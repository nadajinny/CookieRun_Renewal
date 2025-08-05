package ingame;

import java.awt.Image;


public class Field {
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Field)) {
            return false;
        } else {
            Field other = (Field)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$image = this.getImage();
                Object other$image = other.getImage();
                if (this$image == null) {
                    if (other$image != null) {
                        return false;
                    }
                } else if (!this$image.equals(other$image)) {
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
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Field;
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
        return result;
    }

    public String toString() {
        Image var10000 = this.getImage();
        return "Field(image=" + var10000 + ", x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
    }

    public Field() {
    }

    public Field(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}