package ingame;

import javax.swing.ImageIcon;



public class CookieImg {
    private ImageIcon CookieIc;
    private ImageIcon jumpIc;
    private ImageIcon doubleJumpIc;
    private ImageIcon fallIc;
    private ImageIcon slideIc;
    private ImageIcon hitIc;

    public ImageIcon getCookieIc() {return this.CookieIc;}
    public ImageIcon getJumpIc() {return this.jumpIc;}
    public ImageIcon getDoubleJumpIc() {return this.doubleJumpIc;}
    public ImageIcon getFallIc() {return this.fallIc;}
    public ImageIcon getSlideIc() {return this.slideIc;}
    public ImageIcon getHitIc() {return this.hitIc;}


    public void setCookieIc(ImageIcon cookieIc) {
        this.CookieIc = cookieIc;
    }



    public void setJumpIc(ImageIcon jumpIc) {
        this.jumpIc = jumpIc;
    }


    public void setDoubleJumpIc(ImageIcon doubleJumpIc) {
        this.doubleJumpIc = doubleJumpIc;
    }

    public void setFallIc(ImageIcon fallIc) {
        this.fallIc = fallIc;
    }

    public void setSlideIc(ImageIcon slideIc) {
        this.slideIc = slideIc;
    }

    public void setHitIc(ImageIcon hitIc) {
        this.hitIc = hitIc;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CookieImg)) {
            return false;
        } else {
            CookieImg other = (CookieImg)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$cookieIc = this.getCookieIc();
                Object other$cookieIc = other.getCookieIc();
                if (this$cookieIc == null) {
                    if (other$cookieIc != null) {
                        return false;
                    }
                } else if (!this$cookieIc.equals(other$cookieIc)) {
                    return false;
                }

                Object this$jumpIc = this.getJumpIc();
                Object other$jumpIc = other.getJumpIc();
                if (this$jumpIc == null) {
                    if (other$jumpIc != null) {
                        return false;
                    }
                } else if (!this$jumpIc.equals(other$jumpIc)) {
                    return false;
                }

                Object this$doubleJumpIc = this.getDoubleJumpIc();
                Object other$doubleJumpIc = other.getDoubleJumpIc();
                if (this$doubleJumpIc == null) {
                    if (other$doubleJumpIc != null) {
                        return false;
                    }
                } else if (!this$doubleJumpIc.equals(other$doubleJumpIc)) {
                    return false;
                }

                label62: {
                    Object this$fallIc = this.getFallIc();
                    Object other$fallIc = other.getFallIc();
                    if (this$fallIc == null) {
                        if (other$fallIc == null) {
                            break label62;
                        }
                    } else if (this$fallIc.equals(other$fallIc)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$slideIc = this.getSlideIc();
                    Object other$slideIc = other.getSlideIc();
                    if (this$slideIc == null) {
                        if (other$slideIc == null) {
                            break label55;
                        }
                    } else if (this$slideIc.equals(other$slideIc)) {
                        break label55;
                    }

                    return false;
                }

                Object this$hitIc = this.getHitIc();
                Object other$hitIc = other.getHitIc();
                if (this$hitIc == null) {
                    if (other$hitIc != null) {
                        return false;
                    }
                } else if (!this$hitIc.equals(other$hitIc)) {
                    return false;
                }

                return true;
            }
        }
    }
    protected boolean canEqual(Object other) { return other instanceof CookieImg;}

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $cookieIc = this.getCookieIc();
        result = result * 59 + ($cookieIc == null ? 43 : $cookieIc.hashCode());
        Object $jumpIc = this.getJumpIc();
        result = result * 59 + ($jumpIc == null ? 43 : $jumpIc.hashCode());
        Object $doubleJumpIc = this.getDoubleJumpIc();
        result = result * 59 + ($doubleJumpIc == null ? 43 : $doubleJumpIc.hashCode());
        Object $fallIc = this.getFallIc();
        result = result * 59 + ($fallIc == null ? 43 : $fallIc.hashCode());
        Object $slideIc = this.getSlideIc();
        result = result * 59 + ($slideIc == null ? 43 : $slideIc.hashCode());
        Object $hitIc = this.getHitIc();
        result = result * 59 + ($hitIc == null ? 43 : $hitIc.hashCode());
        return result;
    }

    public String toString() {
        ImageIcon var10000 = this.getCookieIc();
        return "CookieImg(cookieIc=" + var10000 + ", jumpIc=" + this.getJumpIc() + ", doubleJumpIc=" + this.getDoubleJumpIc() + ", fallIc=" + this.getFallIc() + ", slideIc=" + this.getSlideIc() + ", hitIc=" + this.getHitIc() + ")";
    }
    public CookieImg(ImageIcon cookieIc, ImageIcon jumpIc, ImageIcon doubleJumpIc, ImageIcon fallIc, ImageIcon slideIc, ImageIcon hitIc) {
        this.CookieIc = cookieIc;
        this.jumpIc = jumpIc;
        this.doubleJumpIc = doubleJumpIc;
        this.fallIc = fallIc;
        this.slideIc = slideIc;
        this.hitIc = hitIc;
    }
}
