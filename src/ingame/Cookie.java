//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ingame;

import java.awt.Image;

public class Cookie {
    private Image image;
    private int x = 160;
    private int y = 0;
    private int width = 80;
    private int height = 120;
    private int alpha = 255;
    private int health = 1000;
    private int big = 0;
    private int fast = 0;
    private int countJump = 0;
    private boolean invincible = false;
    private boolean fall = false;
    private boolean jump = false;
    private boolean visible = true;
    public Cookie(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public int getHealth() {
        return this.health;
    }

    public int getBig() {
        return this.big;
    }

    public int getFast() {
        return this.fast;
    }

    public int getCountJump() {
        return this.countJump;
    }

    public boolean isInvincible() {
        return this.invincible;
    }

    public boolean isFall() {
        return this.fall;
    }

    public boolean isJump() {
        return this.jump;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public void setFast(int fast) {
        this.fast = fast;
    }

    public void setCountJump(int countJump) {
        this.countJump = countJump;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setFall(boolean fall) {
        this.fall = fall;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Cookie)) {
            return false;
        } else {
            Cookie other = (Cookie)o;
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
                } else if (this.getAlpha() != other.getAlpha()) {
                    return false;
                } else if (this.getHealth() != other.getHealth()) {
                    return false;
                } else if (this.getBig() != other.getBig()) {
                    return false;
                } else if (this.getFast() != other.getFast()) {
                    return false;
                } else if (this.getCountJump() != other.getCountJump()) {
                    return false;
                } else if (this.isInvincible() != other.isInvincible()) {
                    return false;
                } else if (this.isFall() != other.isFall()) {
                    return false;
                } else if (this.isJump() != other.isJump()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Cookie;
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
        result = result * 59 + this.getAlpha();
        result = result * 59 + this.getHealth();
        result = result * 59 + this.getBig();
        result = result * 59 + this.getFast();
        result = result * 59 + this.getCountJump();
        result = result * 59 + (this.isInvincible() ? 79 : 97);
        result = result * 59 + (this.isFall() ? 79 : 97);
        result = result * 59 + (this.isJump() ? 79 : 97);
        return result;
    }

    public String toString() {
        Image var10000 = this.getImage();
        return "Cookie(image=" + var10000 + ", x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", alpha=" + this.getAlpha() + ", health=" + this.getHealth() + ", big=" + this.getBig() + ", fast=" + this.getFast() + ", countJump=" + this.getCountJump() + ", invincible=" + this.isInvincible() + ", fall=" + this.isFall() + ", jump=" + this.isJump() + ")";
    }

    public Cookie() {
    }

    public Cookie(Image image, int x, int y, int width, int height, int alpha, int health, int big, int fast, int countJump, boolean invincible, boolean fall, boolean jump) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.health = health;
        this.big = big;
        this.fast = fast;
        this.countJump = countJump;
        this.invincible = invincible;
        this.fall = fall;
        this.jump = jump;
    }
}

