package ingame;

import java.awt.Image;


public class Tacle {

    private Image image; //   ֹ   ̹   

    //   ֹ      ǥ            
    private int x;
    private int y;
    private int width;
    private int height;

    //   ֹ      
    private int state;

    public void setState(int state) {
        this.state = state;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getState() {
        return state;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Image getImage() {
        return image;
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Tacle)) {
            return false;
        } else {
            Tacle other = (Tacle)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label43: {
                    Object this$image = this.getImage();
                    Object other$image = other.getImage();
                    if (this$image == null) {
                        if (other$image == null) {
                            break label43;
                        }
                    } else if (this$image.equals(other$image)) {
                        break label43;
                    }

                    return false;
                }

                if (this.getX() != other.getX()) {
                    return false;
                } else if (this.getY() != other.getY()) {
                    return false;
                } else if (this.getWidth() != other.getWidth()) {
                    return false;
                } else if (this.getHeight() != other.getHeight()) {
                    return false;
                } else {
                    return this.getState() == other.getState();
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Tacle;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $image = this.getImage();
        result = result * 59 + ($image == null ? 43 : $image.hashCode());
        result = result * 59 + this.getX();
        result = result * 59 + this.getY();
        result = result * 59 + this.getWidth();
        result = result * 59 + this.getHeight();
        result = result * 59 + this.getState();
        return result;
    }

    public String toString() {
        Image var10000 = this.getImage();
        return "Tacle(image=" + var10000 + ", x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", state=" + this.getState() + ")";
    }

    public Tacle() {
    }

    public Tacle(Image image, int x, int y, int width, int height, int state) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = state;
    }
}
