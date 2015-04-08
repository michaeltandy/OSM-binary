
package uk.me.mjt.osmpbf.planetproc;


public class SimpleNode {
    private final long id;
    private final int latMillionths;
    private final int lonMillionths;
    private byte countflag = 1;
    
    public SimpleNode(long id, int latMillionths,int lonMillionths) {
        this.id = id;
        this.latMillionths = latMillionths;
        this.lonMillionths = lonMillionths;
    }

    public long getId() {
        return id;
    }
    
    public int getLatMillionths() {
        return latMillionths;
    }

    public int getLonMillionths() {
        return lonMillionths;
    }
    
    

    public byte getCountflag() {
        return countflag;
    }

    public void setCountflag(byte countflag) {
        if (countflag == 0) 
            throw new IllegalArgumentException("Count flag may not be 0.");
        this.countflag = countflag;
    }
    
    public void incrementWayCount() {
        int wayCount = (countflag & 0x0E) >>1; // 3 bits
        if (wayCount < 7) {
            wayCount++;
            countflag = (byte) ((countflag & ~0x0E) | (wayCount<<1));
        }
    }
    
    public int getWayCount() {
        return (countflag & 0x0E) >>1;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + this.latMillionths;
        hash = 23 * hash + this.lonMillionths;
        hash = 23 * hash + this.countflag;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final SimpleNode other = (SimpleNode) obj;
        return (this.id == other.id
                && this.latMillionths == other.latMillionths
                && this.lonMillionths == other.lonMillionths
                && this.countflag == other.countflag);
    }

    @Override
    public String toString() {
        return "SimpleNode{" + "id=" + id + ", latMillionths=" + latMillionths 
                + ", lonMillionths=" + lonMillionths + ", countflag=" + countflag
                 + ", waycount=" + getWayCount() + '}';
    }
    
}
