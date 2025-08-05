package ingame;

import java.awt.Image;

public class Jelly {
    private Image image; // ���� �̹���

    // �������� ��ǥ�� ũ��
    private int x;
    private int y;
    private int width;
    private int height;

    public void setScore(int score) {
        this.score = score;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
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

    public int getScore() {
        return score;
    }

    public int getAlpha() {
        return alpha;
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

    // ������ ���� 0���� 255������
    private int alpha;

    // ������ ����
    private int score;
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Jelly)) {
            return false;
        } else {
            Jelly other = (Jelly)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$image = this.getImage();
                    Object other$image = other.getImage();
                    if (this$image == null) {
                        if (other$image == null) {
                            break label47;
                        }
                    } else if (this$image.equals(other$image)) {
                        break label47;
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
                } else if (this.getAlpha() != other.getAlpha()) {
                    return false;
                } else {
                    return this.getScore() == other.getScore();
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Jelly;
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
        result = result * 59 + this.getScore();
        return result;
    }

    public String toString() {
        Image var10000 = this.getImage();
        return "Jelly(image=" + var10000 + ", x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", alpha=" + this.getAlpha() + ", score=" + this.getScore() + ")";
    }

    public Jelly() {
    }

    public Jelly(Image image, int x, int y, int width, int height, int alpha, int score) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alpha = alpha;
        this.score = score;
    }
}

