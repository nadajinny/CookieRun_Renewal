package ingame;

import javax.swing.ImageIcon;

public class MapObjectImg {
    private ImageIcon backIc; // 첫 번째 배경 이미지
    private ImageIcon secondBackIc; // 두 번째 배경 이미지
    private ImageIcon jelly1Ic;
    private ImageIcon jelly2Ic;
    private ImageIcon jelly3Ic;
    private ImageIcon jellyHPIc;
    private ImageIcon jellyEffectIc;
    private ImageIcon field1Ic; // 필드 이미지
    private ImageIcon field2Ic; // 두 번째 필드 이미지
    private ImageIcon tacle10Ic; // 1칸 장애물
    private ImageIcon tacle20Ic; // 2칸 장애물
    private ImageIcon tacle30Ic; // 3칸 장애물
    private ImageIcon tacle40Ic; // 4칸 장애물

    public void setTacle40Ic(ImageIcon tacle40Ic) {
        this.tacle40Ic = tacle40Ic;
    }

    public void setTacle30Ic(ImageIcon tacle30Ic) {
        this.tacle30Ic = tacle30Ic;
    }

    public void setTacle20Ic(ImageIcon tacle20Ic) {
        this.tacle20Ic = tacle20Ic;
    }

    public void setTacle10Ic(ImageIcon tacle10Ic) {
        this.tacle10Ic = tacle10Ic;
    }

    public void setField2Ic(ImageIcon field2Ic) {
        this.field2Ic = field2Ic;
    }

    public void setField1Ic(ImageIcon field1Ic) {
        this.field1Ic = field1Ic;
    }

    public void setJellyEffectIc(ImageIcon jellyEffectIc) {
        this.jellyEffectIc = jellyEffectIc;
    }

    public void setJellyHPIc(ImageIcon jellyHPIc) {
        this.jellyHPIc = jellyHPIc;
    }

    public void setJelly3Ic(ImageIcon jelly3Ic) {
        this.jelly3Ic = jelly3Ic;
    }

    public void setJelly2Ic(ImageIcon jelly2Ic) {
        this.jelly2Ic = jelly2Ic;
    }

    public void setJelly1Ic(ImageIcon jelly1Ic) {
        this.jelly1Ic = jelly1Ic;
    }

    public void setSecondBackIc(ImageIcon secondBackIc) {
        this.secondBackIc = secondBackIc;
    }

    public void setBackIc(ImageIcon backIc) {
        this.backIc = backIc;
    }

    public ImageIcon getTacle40Ic() {
        return tacle40Ic;
    }

    public ImageIcon getTacle30Ic() {
        return tacle30Ic;
    }

    public ImageIcon getTacle20Ic() {
        return tacle20Ic;
    }

    public ImageIcon getTacle10Ic() {
        return tacle10Ic;
    }

    public ImageIcon getField2Ic() {
        return field2Ic;
    }

    public ImageIcon getField1Ic() {
        return field1Ic;
    }

    public ImageIcon getJellyEffectIc() {
        return jellyEffectIc;
    }

    public ImageIcon getJellyHPIc() {
        return jellyHPIc;
    }

    public ImageIcon getJelly3Ic() {
        return jelly3Ic;
    }

    public ImageIcon getJelly2Ic() {
        return jelly2Ic;
    }

    public ImageIcon getJelly1Ic() {
        return jelly1Ic;
    }

    public ImageIcon getSecondBackIc() {
        return secondBackIc;
    }

    public ImageIcon getBackIc() {
        return backIc;
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MapObjectImg)) {
            return false;
        } else {
            MapObjectImg other = (MapObjectImg)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label167: {
                    Object this$backIc = this.getBackIc();
                    Object other$backIc = other.getBackIc();
                    if (this$backIc == null) {
                        if (other$backIc == null) {
                            break label167;
                        }
                    } else if (this$backIc.equals(other$backIc)) {
                        break label167;
                    }

                    return false;
                }

                Object this$secondBackIc = this.getSecondBackIc();
                Object other$secondBackIc = other.getSecondBackIc();
                if (this$secondBackIc == null) {
                    if (other$secondBackIc != null) {
                        return false;
                    }
                } else if (!this$secondBackIc.equals(other$secondBackIc)) {
                    return false;
                }

                label153: {
                    Object this$jelly1Ic = this.getJelly1Ic();
                    Object other$jelly1Ic = other.getJelly1Ic();
                    if (this$jelly1Ic == null) {
                        if (other$jelly1Ic == null) {
                            break label153;
                        }
                    } else if (this$jelly1Ic.equals(other$jelly1Ic)) {
                        break label153;
                    }

                    return false;
                }

                Object this$jelly2Ic = this.getJelly2Ic();
                Object other$jelly2Ic = other.getJelly2Ic();
                if (this$jelly2Ic == null) {
                    if (other$jelly2Ic != null) {
                        return false;
                    }
                } else if (!this$jelly2Ic.equals(other$jelly2Ic)) {
                    return false;
                }

                label139: {
                    Object this$jelly3Ic = this.getJelly3Ic();
                    Object other$jelly3Ic = other.getJelly3Ic();
                    if (this$jelly3Ic == null) {
                        if (other$jelly3Ic == null) {
                            break label139;
                        }
                    } else if (this$jelly3Ic.equals(other$jelly3Ic)) {
                        break label139;
                    }

                    return false;
                }

                Object this$jellyHPIc = this.getJellyHPIc();
                Object other$jellyHPIc = other.getJellyHPIc();
                if (this$jellyHPIc == null) {
                    if (other$jellyHPIc != null) {
                        return false;
                    }
                } else if (!this$jellyHPIc.equals(other$jellyHPIc)) {
                    return false;
                }

                label125: {
                    Object this$jellyEffectIc = this.getJellyEffectIc();
                    Object other$jellyEffectIc = other.getJellyEffectIc();
                    if (this$jellyEffectIc == null) {
                        if (other$jellyEffectIc == null) {
                            break label125;
                        }
                    } else if (this$jellyEffectIc.equals(other$jellyEffectIc)) {
                        break label125;
                    }

                    return false;
                }

                label118: {
                    Object this$field1Ic = this.getField1Ic();
                    Object other$field1Ic = other.getField1Ic();
                    if (this$field1Ic == null) {
                        if (other$field1Ic == null) {
                            break label118;
                        }
                    } else if (this$field1Ic.equals(other$field1Ic)) {
                        break label118;
                    }

                    return false;
                }

                Object this$field2Ic = this.getField2Ic();
                Object other$field2Ic = other.getField2Ic();
                if (this$field2Ic == null) {
                    if (other$field2Ic != null) {
                        return false;
                    }
                } else if (!this$field2Ic.equals(other$field2Ic)) {
                    return false;
                }

                label104: {
                    Object this$tacle10Ic = this.getTacle10Ic();
                    Object other$tacle10Ic = other.getTacle10Ic();
                    if (this$tacle10Ic == null) {
                        if (other$tacle10Ic == null) {
                            break label104;
                        }
                    } else if (this$tacle10Ic.equals(other$tacle10Ic)) {
                        break label104;
                    }

                    return false;
                }

                label97: {
                    Object this$tacle20Ic = this.getTacle20Ic();
                    Object other$tacle20Ic = other.getTacle20Ic();
                    if (this$tacle20Ic == null) {
                        if (other$tacle20Ic == null) {
                            break label97;
                        }
                    } else if (this$tacle20Ic.equals(other$tacle20Ic)) {
                        break label97;
                    }

                    return false;
                }

                Object this$tacle30Ic = this.getTacle30Ic();
                Object other$tacle30Ic = other.getTacle30Ic();
                if (this$tacle30Ic == null) {
                    if (other$tacle30Ic != null) {
                        return false;
                    }
                } else if (!this$tacle30Ic.equals(other$tacle30Ic)) {
                    return false;
                }

                Object this$tacle40Ic = this.getTacle40Ic();
                Object other$tacle40Ic = other.getTacle40Ic();
                if (this$tacle40Ic == null) {
                    if (other$tacle40Ic != null) {
                        return false;
                    }
                } else if (!this$tacle40Ic.equals(other$tacle40Ic)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof MapObjectImg;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $backIc = this.getBackIc();
        result = result * 59 + ($backIc == null ? 43 : $backIc.hashCode());
        Object $secondBackIc = this.getSecondBackIc();
        result = result * 59 + ($secondBackIc == null ? 43 : $secondBackIc.hashCode());
        Object $jelly1Ic = this.getJelly1Ic();
        result = result * 59 + ($jelly1Ic == null ? 43 : $jelly1Ic.hashCode());
        Object $jelly2Ic = this.getJelly2Ic();
        result = result * 59 + ($jelly2Ic == null ? 43 : $jelly2Ic.hashCode());
        Object $jelly3Ic = this.getJelly3Ic();
        result = result * 59 + ($jelly3Ic == null ? 43 : $jelly3Ic.hashCode());
        Object $jellyHPIc = this.getJellyHPIc();
        result = result * 59 + ($jellyHPIc == null ? 43 : $jellyHPIc.hashCode());
        Object $jellyEffectIc = this.getJellyEffectIc();
        result = result * 59 + ($jellyEffectIc == null ? 43 : $jellyEffectIc.hashCode());
        Object $field1Ic = this.getField1Ic();
        result = result * 59 + ($field1Ic == null ? 43 : $field1Ic.hashCode());
        Object $field2Ic = this.getField2Ic();
        result = result * 59 + ($field2Ic == null ? 43 : $field2Ic.hashCode());
        Object $tacle10Ic = this.getTacle10Ic();
        result = result * 59 + ($tacle10Ic == null ? 43 : $tacle10Ic.hashCode());
        Object $tacle20Ic = this.getTacle20Ic();
        result = result * 59 + ($tacle20Ic == null ? 43 : $tacle20Ic.hashCode());
        Object $tacle30Ic = this.getTacle30Ic();
        result = result * 59 + ($tacle30Ic == null ? 43 : $tacle30Ic.hashCode());
        Object $tacle40Ic = this.getTacle40Ic();
        result = result * 59 + ($tacle40Ic == null ? 43 : $tacle40Ic.hashCode());
        return result;
    }

    public String toString() {
        ImageIcon var10000 = this.getBackIc();
        return "MapObjectImg(backIc=" + var10000 + ", secondBackIc=" + this.getSecondBackIc() + ", jelly1Ic=" + this.getJelly1Ic() + ", jelly2Ic=" + this.getJelly2Ic() + ", jelly3Ic=" + this.getJelly3Ic() + ", jellyHPIc=" + this.getJellyHPIc() + ", jellyEffectIc=" + this.getJellyEffectIc() + ", field1Ic=" + this.getField1Ic() + ", field2Ic=" + this.getField2Ic() + ", tacle10Ic=" + this.getTacle10Ic() + ", tacle20Ic=" + this.getTacle20Ic() + ", tacle30Ic=" + this.getTacle30Ic() + ", tacle40Ic=" + this.getTacle40Ic() + ")";
    }

    public MapObjectImg() {
    }

    public MapObjectImg(ImageIcon backIc, ImageIcon secondBackIc, ImageIcon jelly1Ic, ImageIcon jelly2Ic, ImageIcon jelly3Ic, ImageIcon jellyHPIc, ImageIcon jellyEffectIc, ImageIcon field1Ic, ImageIcon field2Ic, ImageIcon tacle10Ic, ImageIcon tacle20Ic, ImageIcon tacle30Ic, ImageIcon tacle40Ic) {
        this.backIc = backIc;
        this.secondBackIc = secondBackIc;
        this.jelly1Ic = jelly1Ic;
        this.jelly2Ic = jelly2Ic;
        this.jelly3Ic = jelly3Ic;
        this.jellyHPIc = jellyHPIc;
        this.jellyEffectIc = jellyEffectIc;
        this.field1Ic = field1Ic;
        this.field2Ic = field2Ic;
        this.tacle10Ic = tacle10Ic;
        this.tacle20Ic = tacle20Ic;
        this.tacle30Ic = tacle30Ic;
        this.tacle40Ic = tacle40Ic;
    }
}