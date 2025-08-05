package panels;

import ingame.CookieImg;
import ingame.Jelly;
import ingame.Field;
import ingame.Tacle;
import ingame.Back;
import ingame.Cookie;
import ingame.MapObjectImg;

import java.io.IOException;
import java.io.DataOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class RelayGamePanel extends JPanel {
    private ImageIcon cookieIc1, jumpIc1, doubleJumpIc1, fallIc1, slideIc1, hitIc1;
    private ImageIcon cookieIc2, jumpIc2, doubleJumpIc2, fallIc2, slideIc2, hitIc2;
    private ImageIcon backIc, secondBackIc, backIc2, secondBackIc2, backIc3, secondBackIc3, backIc4, secondBackIc4;
    private ImageIcon jelly1Ic, jelly2Ic, jelly3Ic, jellyHPIc, jellyEffectIc;
    private ImageIcon field1Ic, field2Ic;
    private ImageIcon tacle10Ic, tacle20Ic, tacle30Ic, tacle40Ic;
    private ImageIcon lifeBar, redBg, jumpButtonIconUp, jumpButtonIconDown, slideIconUp, slideIconDown;
    private Image jumpBtn, slideBtn;

    private List<Jelly> jellyList;
    private List<Field> fieldList;
    private List<Tacle> tacleList;
    private List<Integer> mapLengthList;

    private int mapLength = 0;
    private int runPage = 0;
    private int runStage = 1;
    private int resultScore = 0;
    private int gameSpeed = 5;
    private int nowField = 2000;

    private JButton escButton;
    private boolean fadeOn = false;
    private boolean escKeyOn = false;
    private boolean downKeyOn1 = false;
    private boolean downKeyOn2 = false;
    private boolean redScreen = false;
    private int face;
    private int foot;

    private int[] sizeArr;
    private int[][] colorArr;

    private Image buffImage;
    private Graphics buffg;
    private AlphaComposite alphaComposite;

    private Cookie c1;
    private Cookie c2;
    private Back b11, b12, b21, b22;
    private Color backFade;
    private MapObjectImg mo1, mo2, mo3, mo4;

    private JFrame superFrame;
    private CardLayout cl;
    private DataOutputStream dos;
    private boolean isPlayer1Turn = true;
    private boolean isLocalPlayer1;
    private boolean isTeam1;

    public RelayGamePanel(JFrame superFrame, CardLayout cl, DataOutputStream dos, CookieImg ci1, CookieImg ci2, boolean isLocalPlayer1, boolean isTeam1) throws Exception {
        this.superFrame = superFrame;
        this.cl = cl;
        this.dos = dos;
        this.isLocalPlayer1 = isLocalPlayer1;
        this.isTeam1 = isTeam1;

        escButton = new JButton("back");
        escButton.setBounds(350, 200, 100, 30);
        escButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(escButton);
                escKeyOn = false;
            }
        });

        gameSet(ci1, ci2);
    }

    public void gameSet(CookieImg ci1, CookieImg ci2) throws Exception {
        setFocusable(true);
        requestFocusInWindow(); // 포커스를 요청합니다.
        initCookieImg(ci1, ci2);
        initObject();
        initListener();
        runRepaint();
    }

    public void gameStart() {
        requestFocusInWindow(); // 게임이 시작될 때 포커스를 요청합니다.
        mapMove();
        fall(); // 통합된 fall 메서드
    }


    @Override
    protected void paintComponent(Graphics g) {
        if (buffg == null) {
            buffImage = createImage(this.getWidth(), this.getHeight());
            if (buffImage == null) {
                System.out.println("Error: Buffer image is null.");
            } else {
                buffg = buffImage.getGraphics();
            }
        }

        Graphics2D g2 = (Graphics2D) buffg;

        super.paintComponent(buffg);

        buffg.drawImage(b11.getImage(), b11.getX(), 0, b11.getWidth(), b11.getHeight() * 5 / 4, null);
        buffg.drawImage(b12.getImage(), b12.getX(), 0, b12.getWidth(), b12.getHeight() * 5 / 4, null);
        buffg.drawImage(b21.getImage(), b21.getX(), 0, b21.getWidth(), b21.getHeight() * 5 / 4, null);
        buffg.drawImage(b22.getImage(), b22.getX(), 0, b22.getWidth(), b22.getHeight() * 5 / 4, null);

        if (fadeOn) {
            buffg.setColor(backFade);
            buffg.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        synchronized (fieldList) {
            for (Field tempFoot : fieldList) {
                if (tempFoot.getX() > -90 && tempFoot.getX() < 810) {
                    buffg.drawImage(tempFoot.getImage(), tempFoot.getX(), tempFoot.getY(), tempFoot.getWidth(), tempFoot.getHeight(), null);
                }
            }
        }

        synchronized (jellyList) {
            for (Jelly tempJelly : jellyList) {
                if (tempJelly.getX() > -90 && tempJelly.getX() < 810) {
                    alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) tempJelly.getAlpha() / 255);
                    g2.setComposite(alphaComposite);
                    buffg.drawImage(tempJelly.getImage(), tempJelly.getX(), tempJelly.getY(), tempJelly.getWidth(), tempJelly.getHeight(), null);
                    alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
                    g2.setComposite(alphaComposite);
                }
            }
        }

        synchronized (tacleList) {
            for (Tacle tempTacle : tacleList) {
                if (tempTacle.getX() > -90 && tempTacle.getX() < 810) {
                    buffg.drawImage(tempTacle.getImage(), tempTacle.getX(), tempTacle.getY(), tempTacle.getWidth(), tempTacle.getHeight(), null);
                }
            }
        }

        if (c1.isVisible()) {
            if (c1.isInvincible()) {
                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c1.getAlpha() / 255);
                g2.setComposite(alphaComposite);
                buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170, cookieIc1.getImage().getWidth(null) * 8 / 10, cookieIc1.getImage().getHeight(null) * 8 / 10, null);
                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
                g2.setComposite(alphaComposite);
            } else {
                buffg.drawImage(c1.getImage(), c1.getX() - 110, c1.getY() - 170, cookieIc1.getImage().getWidth(null) * 8 / 10, cookieIc1.getImage().getHeight(null) * 8 / 10, null);
            }
        }

        if (c2.isVisible()) {
            if (c2.isInvincible()) {
                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) c2.getAlpha() / 255);
                g2.setComposite(alphaComposite);
                buffg.drawImage(c2.getImage(), c2.getX() - 110, c2.getY() - 170, cookieIc2.getImage().getWidth(null) * 8 / 10, cookieIc2.getImage().getHeight(null) * 8 / 10, null);
                alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
                g2.setComposite(alphaComposite);
            } else {
                buffg.drawImage(c2.getImage(), c2.getX() - 110, c2.getY() - 170, cookieIc2.getImage().getWidth(null) * 8 / 10, cookieIc2.getImage().getHeight(null) * 8 / 10, null);
            }
        }

        if (redScreen) {
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 125 / 255);
            g2.setComposite(alphaComposite);
            buffg.drawImage(redBg.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
            g2.setComposite(alphaComposite);
        }

        Util.drawFancyString(g2, Integer.toString(resultScore), 600, 58, 30, Color.WHITE);

        // 체력바 업데이트
        if (isPlayer1Turn) {
            updateLifeBar(c1);
        } else {
            updateLifeBar(c2);
        }

        buffg.drawImage(jumpBtn, 0, 360, 132, 100, null);
        buffg.drawImage(slideBtn, 650, 360, 132, 100, null);

        if (escKeyOn) {
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 100 / 255);
            g2.setComposite(alphaComposite);
            buffg.setColor(Color.BLACK);
            buffg.fillRect(0, 0, 850, 550);
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 255 / 255);
            g2.setComposite(alphaComposite);
        }

        g.drawImage(buffImage, 0, 0, this);
    }


    private void makeMo() {
        mo1 = new MapObjectImg(
                new ImageIcon("img/Objectimg/map1img/bg1.png"),
                new ImageIcon("img/Objectimg/map1img/bg2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly1.png"),
                new ImageIcon("img/Objectimg/map1img/jelly2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly3.png"),
                new ImageIcon("img/Objectimg/map1img/life.png"),
                new ImageIcon("img/Objectimg/map1img/effectTest.png"),
                new ImageIcon("img/Objectimg/map1img/fieldIc1.png"),
                new ImageIcon("img/Objectimg/map1img/fieldIc2.png"),
                new ImageIcon("img/Objectimg/map1img/tacle1.gif"),
                new ImageIcon("img/Objectimg/map1img/tacle2.png"),
                new ImageIcon("img/Objectimg/map1img/tacle3.png"),
                new ImageIcon("img/Objectimg/map1img/tacle3.png")
        );

        mo2 = new MapObjectImg(
                new ImageIcon("img/Objectimg/map2img/back1.png"),
                new ImageIcon("img/Objectimg/map2img/back2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly1.png"),
                new ImageIcon("img/Objectimg/map1img/jelly2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly3.png"),
                new ImageIcon("img/Objectimg/map1img/life.png"),
                new ImageIcon("img/Objectimg/map1img/effectTest.png"),
                new ImageIcon("img/Objectimg/map2img/field1.png"),
                new ImageIcon("img/Objectimg/map2img/field2.png"),
                new ImageIcon("img/Objectimg/map2img/tacle1.png"),
                new ImageIcon("img/Objectimg/map2img/tacle2.png"),
                new ImageIcon("img/Objectimg/map2img/tacle3.png"),
                new ImageIcon("img/Objectimg/map2img/tacle3.png")
        );

        mo3 = new MapObjectImg(
                new ImageIcon("img/Objectimg/map3img/bg.png"),
                new ImageIcon("img/Objectimg/map3img/bg2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly1.png"),
                new ImageIcon("img/Objectimg/map1img/jelly2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly3.png"),
                new ImageIcon("img/Objectimg/map1img/life.png"),
                new ImageIcon("img/Objectimg/map1img/effectTest.png"),
                new ImageIcon("img/Objectimg/map3img/field.png"),
                new ImageIcon("img/Objectimg/map3img/field2.png"),
                new ImageIcon("img/Objectimg/map3img/tacle1.png"),
                new ImageIcon("img/Objectimg/map3img/tacle2.png"),
                new ImageIcon("img/Objectimg/map3img/tacle3.png"),
                new ImageIcon("img/Objectimg/map3img/tacle3.png")
        );

        mo4 = new MapObjectImg(
                new ImageIcon("img/Objectimg/map4img/bback.png"),
                new ImageIcon("img/Objectimg/map4img/bback2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly1.png"),
                new ImageIcon("img/Objectimg/map1img/jelly2.png"),
                new ImageIcon("img/Objectimg/map1img/jelly3.png"),
                new ImageIcon("img/Objectimg/map1img/life.png"),
                new ImageIcon("img/Objectimg/map1img/effectTest.png"),
                new ImageIcon("img/Objectimg/map4img/ffootTest.png"),
                new ImageIcon("img/Objectimg/map4img/ffootTest2.png"),
                new ImageIcon("img/Objectimg/map4img/tacle1.png"),
                new ImageIcon("img/Objectimg/map4img/tacle2.png"),
                new ImageIcon("img/Objectimg/map4img/tacle2.png"),
                new ImageIcon("img/Objectimg/map4img/tacle2.png")
        );
    }

    private void initCookieImg(CookieImg ci1, CookieImg ci2) {
        cookieIc1 = ci1.getCookieIc();
        jumpIc1 = ci1.getJumpIc();
        doubleJumpIc1 = ci1.getDoubleJumpIc();
        fallIc1 = ci1.getFallIc();
        slideIc1 = ci1.getSlideIc();
        hitIc1 = ci1.getHitIc();

        cookieIc2 = ci2.getCookieIc();
        jumpIc2 = ci2.getJumpIc();
        doubleJumpIc2 = ci2.getDoubleJumpIc();
        fallIc2 = ci2.getFallIc();
        slideIc2 = ci2.getSlideIc();
        hitIc2 = ci2.getHitIc();
    }

    private void initImageIcon(MapObjectImg mo) {
        jelly1Ic = mo.getJelly1Ic();
        jelly2Ic = mo.getJelly2Ic();
        jelly3Ic = mo.getJelly3Ic();
        jellyHPIc = mo.getJellyHPIc();
        jellyEffectIc = mo.getJellyEffectIc();

        field1Ic = mo.getField1Ic();
        field2Ic = mo.getField2Ic();

        tacle10Ic = mo.getTacle10Ic();
        tacle20Ic = mo.getTacle20Ic();
        tacle30Ic = mo.getTacle30Ic();
        tacle40Ic = mo.getTacle40Ic();
    }

    private void initMap(int num, int mapLength) throws Exception {
        String tempMap = null;
        int tempMapLength = 0;

        if (num == 1) {
            tempMap = "img/map/map1.png";
        } else if (num == 2) {
            tempMap = "img/map/map2.png";
        } else if (num == 3) {
            tempMap = "img/map/map3.png";
        } else if (num == 4) {
            tempMap = "img/map/map4.png";
        }

        try {
            sizeArr = Util.getSize(tempMap);
            colorArr = Util.getPic(tempMap);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        tempMapLength = sizeArr[0];
        int maxX = sizeArr[0];
        int maxY = sizeArr[1];

        for (int i = 0; i < maxX; i += 1) {
            for (int j = 0; j < maxY; j += 1) {
                if (colorArr[i][j] == 16776960) {
                    jellyList.add(new Jelly(jelly1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 1234));
                } else if (colorArr[i][j] == 13158400) {
                    jellyList.add(new Jelly(jelly2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 2345));
                } else if (colorArr[i][j] == 9868800) {
                    jellyList.add(new Jelly(jelly3Ic.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 3456));
                } else if (colorArr[i][j] == 16737280) {
                    jellyList.add(new Jelly(jellyHPIc.getImage(), i * 40 + mapLength * 40, j * 40, 30, 30, 255, 4567));
                }
            }
        }

        for (int i = 0; i < maxX; i += 2) {
            for (int j = 0; j < maxY; j += 2) {
                if (colorArr[i][j] == 0) {
                    fieldList.add(new Field(field1Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));
                } else if (colorArr[i][j] == 6579300) {
                    fieldList.add(new Field(field2Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80));
                }
            }
        }

        for (int i = 0; i < maxX; i += 2) {
            for (int j = 0; j < maxY; j += 2) {
                if (colorArr[i][j] == 16711680) {
                    tacleList.add(new Tacle(tacle10Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 80, 0));
                } else if (colorArr[i][j] == 16711830) {
                    tacleList.add(new Tacle(tacle20Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 160, 0));
                } else if (colorArr[i][j] == 16711935) {
                    tacleList.add(new Tacle(tacle30Ic.getImage(), i * 40 + mapLength * 40, j * 40, 80, 240, 0));
                }
            }
        }

        this.mapLength = this.mapLength + tempMapLength;
    }

    private void initObject() throws Exception {
        lifeBar = new ImageIcon("img/Objectimg/lifebar/lifeBar1.png");
        redBg = new ImageIcon("img/Objectimg/lifebar/redBg.png");
        jumpButtonIconUp = new ImageIcon("img/Objectimg/lifebar/jumpno.png");
        jumpButtonIconDown = new ImageIcon("img/Objectimg/lifebar/jumpdim.png");
        slideIconUp = new ImageIcon("img/Objectimg/lifebar/slideno.png");
        slideIconDown = new ImageIcon("img/Objectimg/lifebar/slidedim.png");
        jumpBtn = jumpButtonIconUp.getImage();
        slideBtn = slideIconUp.getImage();

        jellyList = new ArrayList<>();
        fieldList = new ArrayList<>();
        tacleList = new ArrayList<>();
        mapLengthList = new ArrayList<>();

        makeMo();

        initImageIcon(mo1);
        initMap(1, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo2);
        initMap(2, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo3);
        initMap(3, mapLength);
        mapLengthList.add(mapLength);

        initImageIcon(mo4);
        initMap(4, mapLength);

        backIc = mo1.getBackIc();
        secondBackIc = mo1.getSecondBackIc();
        backIc2 = mo2.getBackIc();
        secondBackIc2 = mo2.getSecondBackIc();
        backIc3 = mo3.getBackIc();
        secondBackIc3 = mo3.getSecondBackIc();
        backIc4 = mo4.getBackIc();
        secondBackIc4 = mo4.getSecondBackIc();

        c1 = new Cookie(cookieIc1.getImage());
        c2 = new Cookie(cookieIc2.getImage());
        c2.setVisible(false);

        face = c1.getX() + c1.getWidth();
        foot = c1.getY() + c1.getHeight();

        b11 = new Back(backIc.getImage(), 0, 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));
        b12 = new Back(backIc.getImage(), backIc.getImage().getWidth(null), 0, backIc.getImage().getWidth(null), backIc.getImage().getHeight(null));
        b21 = new Back(secondBackIc.getImage(), 0, 0, secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));
        b22 = new Back(secondBackIc.getImage(), secondBackIc.getImage().getWidth(null), 0, secondBackIc.getImage().getWidth(null), secondBackIc.getImage().getHeight(null));

        backFade = new Color(0, 0, 0, 0);
    }

    private void initListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (escKeyOn) return;

                if ((isPlayer1Turn && isLocalPlayer1) || (!isPlayer1Turn && !isLocalPlayer1)) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        if (!escKeyOn) {
                            escKeyOn = true;
                            add(escButton);
                            repaint();
                        } else {
                            remove(escButton);
                            escKeyOn = false;
                        }
                    }

                    if (isPlayer1Turn) {
                        handleKeyPressPlayer1(e);
                    } else {
                        handleKeyPressPlayer2(e);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if ((isPlayer1Turn && isLocalPlayer1) || (!isPlayer1Turn && !isLocalPlayer1)) {
                    if (isPlayer1Turn) {
                        handleKeyReleasePlayer1(e);
                    } else {
                        handleKeyReleasePlayer2(e);
                    }
                }
            }
        });
    }

    private void handleKeyPressPlayer1(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            sendGameMessage("jump");
            jump1();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            sendGameMessage("slide_down");
            downKeyOn1 = true;
            if (c1.getImage() != slideIc1.getImage() && !c1.isJump() && !c1.isFall()) {
                c1.setImage(slideIc1.getImage());
            }
        }
    }

    private void handleKeyReleasePlayer1(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            sendGameMessage("slide_up");
            downKeyOn1 = false;
            if (c1.getImage() != cookieIc1.getImage() && !c1.isJump() && !c1.isFall()) {
                c1.setImage(cookieIc1.getImage());
            }
        }
    }

    private void handleKeyPressPlayer2(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            sendGameMessage("jump");
            jump2();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            sendGameMessage("slide_down");
            downKeyOn2 = true;
            if (c2.getImage() != slideIc2.getImage() && !c2.isJump() && !c2.isFall()) {
                c2.setImage(slideIc2.getImage());
            }
        }
    }

    private void handleKeyReleasePlayer2(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            sendGameMessage("slide_up");
            downKeyOn2 = false;
            if (c2.getImage() != cookieIc2.getImage() && !c2.isJump() && !c2.isFall()) {
                c2.setImage(cookieIc2.getImage());
            }
        }
    }

    private void sendGameMessage(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runRepaint() {
        new Thread(() -> {
            while (true) {
                repaint();

                if (escKeyOn) {
                    while (escKeyOn) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void mapMove() {
        new Thread(() -> {
            while (true) {
                if (runPage > 800) {
                    if (isPlayer1Turn) {
                        c1.setHealth(c1.getHealth() - 20);
                    } else {
                        c2.setHealth(c2.getHealth() - 20);
                    }
                    runPage = 0;
                }

                runPage += gameSpeed;

                if (isPlayer1Turn) {
                    foot = c1.getY() + c1.getHeight();
                    if (foot > 1999 || c1.getHealth() < 1) {
                        switchToPlayer2();
                        continue;
                    }
                } else {
                    foot = c2.getY() + c2.getHeight();
                    if (foot > 1999 || c2.getHealth() < 1) {
                        endGame();
                        return;
                    }
                }

                if (!fadeOn) {
                    handleMapTransition();
                }

                mapLength += gameSpeed;

                if (b11.getX() < -(b11.getWidth() - 1)) {
                    b11.setX(b11.getWidth());
                }
                if (b12.getX() < -(b12.getWidth() - 1)) {
                    b12.setX(b11.getWidth());
                }
                if (b21.getX() < -(b21.getWidth() - 1)) {
                    b21.setX(b21.getWidth());
                }
                if (b22.getX() < -(b21.getWidth() - 1)) {
                    b22.setX(b21.getWidth());
                }

                b11.setX(b11.getX() - gameSpeed / 3);
                b12.setX(b12.getX() - gameSpeed / 3);
                b21.setX(b21.getX() - gameSpeed * 2 / 3);
                b22.setX(b21.getX() - gameSpeed * 2 / 3);

                synchronized (fieldList) {
                    fieldList.removeIf(tempField -> tempField.getX() < -90);

                    for (Field tempField : fieldList) {
                        tempField.setX(tempField.getX() - gameSpeed);
                    }
                }

                synchronized (jellyList) {
                    jellyList.removeIf(tempJelly -> tempJelly.getX() < -90);

                    for (Jelly tempJelly : jellyList) {
                        tempJelly.setX(tempJelly.getX() - gameSpeed);
                        if (tempJelly.getImage() == jellyEffectIc.getImage() && tempJelly.getAlpha() > 4) {
                            tempJelly.setAlpha(tempJelly.getAlpha() - 5);
                        }

                        foot = isPlayer1Turn ? c1.getY() + c1.getHeight() : c2.getY() + c2.getHeight();
                        Cookie currentPlayer = isPlayer1Turn ? c1 : c2;

                        if (currentPlayer.getImage() != (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= currentPlayer.getX() && tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face && tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= currentPlayer.getY() && tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot && tempJelly.getImage() != jellyEffectIc.getImage()) {
                            if (tempJelly.getImage() == jellyHPIc.getImage()) {
                                if ((currentPlayer.getHealth() + 100) > 1000) {
                                    currentPlayer.setHealth(1000);
                                } else {
                                    currentPlayer.setHealth(currentPlayer.getHealth() + 100);
                                }
                            }
                            tempJelly.setImage(jellyEffectIc.getImage());
                            resultScore = resultScore + tempJelly.getScore();
                        } else if (currentPlayer.getImage() == (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempJelly.getX() + tempJelly.getWidth() * 20 / 100 >= currentPlayer.getX() && tempJelly.getX() + tempJelly.getWidth() * 80 / 100 <= face && tempJelly.getY() + tempJelly.getWidth() * 20 / 100 >= currentPlayer.getY() + currentPlayer.getHeight() * 1 / 3 && tempJelly.getY() + tempJelly.getWidth() * 80 / 100 <= foot && tempJelly.getImage() != jellyEffectIc.getImage()) {
                            if (tempJelly.getImage() == jellyHPIc.getImage()) {
                                if ((currentPlayer.getHealth() + 100) > 1000) {
                                    currentPlayer.setHealth(1000);
                                } else {
                                    currentPlayer.setHealth(currentPlayer.getHealth() + 100);
                                }
                            }
                            tempJelly.setImage(jellyEffectIc.getImage());
                            resultScore = resultScore + tempJelly.getScore();
                        }
                    }
                }

                synchronized (tacleList) {
                    tacleList.removeIf(tempTacle -> tempTacle.getX() < -90);

                    for (Tacle tempTacle : tacleList) {
                        tempTacle.setX(tempTacle.getX() - gameSpeed);

                        face = isPlayer1Turn ? c1.getX() + c1.getWidth() : c2.getX() + c2.getWidth();
                        foot = isPlayer1Turn ? c1.getY() + c1.getHeight() : c2.getY() + c2.getHeight();
                        Cookie currentPlayer = isPlayer1Turn ? c1 : c2;

                        if (!currentPlayer.isInvincible() && currentPlayer.getImage() != (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempTacle.getX() + tempTacle.getWidth() / 2 >= currentPlayer.getX() && tempTacle.getX() + tempTacle.getWidth() / 2 <= face && tempTacle.getY() + tempTacle.getHeight() / 2 >= currentPlayer.getY() && tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {
                            hit();
                        } else if (!currentPlayer.isInvincible() && currentPlayer.getImage() != (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempTacle.getX() + tempTacle.getWidth() / 2 >= currentPlayer.getX() && tempTacle.getX() + tempTacle.getWidth() / 2 <= face && tempTacle.getY() <= currentPlayer.getY() && tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > currentPlayer.getY()) {
                            hit();
                        } else if (!currentPlayer.isInvincible() && currentPlayer.getImage() == (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempTacle.getX() + tempTacle.getWidth() / 2 >= currentPlayer.getX() && tempTacle.getX() + tempTacle.getWidth() / 2 <= face && tempTacle.getY() + tempTacle.getHeight() / 2 >= currentPlayer.getY() + currentPlayer.getHeight() * 2 / 3 && tempTacle.getY() + tempTacle.getHeight() / 2 <= foot) {
                            hit();
                        } else if (!currentPlayer.isInvincible() && currentPlayer.getImage() == (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage()) && tempTacle.getX() + tempTacle.getWidth() / 2 >= currentPlayer.getX() && tempTacle.getX() + tempTacle.getWidth() / 2 <= face && tempTacle.getY() < currentPlayer.getY() && tempTacle.getY() + tempTacle.getHeight() * 95 / 100 > currentPlayer.getY() + currentPlayer.getHeight() * 2 / 3) {
                            hit();
                        }
                    }
                }

                int tempNowField = isPlayer1Turn ? (c1.isInvincible() ? 400 : 2000) : (c2.isInvincible() ? 400 : 2000);

                for (Field field : fieldList) {
                    int tempX = field.getX();
                    if (tempX > (isPlayer1Turn ? c1.getX() - 60 : c2.getX() - 60) && tempX <= face) {
                        int tempField = field.getY();
                        foot = isPlayer1Turn ? c1.getY() + c1.getHeight() : c2.getY() + c2.getHeight();
                        if (tempField < tempNowField && tempField >= foot) {
                            tempNowField = tempField;
                        }
                    }
                }

                nowField = tempNowField;

                // 게임 종료 조건 체크
                checkGameEndCondition();

                if (escKeyOn) {
                    while (escKeyOn) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkGameEndCondition() {
        if (!isPlayer1Turn) {
            if (c2.getHealth() <= 0 || c2.getY() + c2.getHeight() > 1999) {
                sendGameMessage("endGame");
                endGame();
            }
        }
    }

    private void switchToPlayer2() {
        if (isPlayer1Turn) {
            c1.setVisible(false);
            c2.setVisible(true);
            c2.setHealth(1000); // 체력 재설정
            c2.setY(0); // Ensure c2 starts from the top
            hitEffectWithoutHealthLoss(c2); // Apply hit effect without health loss
            isPlayer1Turn = false;
            fall();
            repaint();
        }
    }

    private void hit() {
        new Thread(() -> {
            Cookie currentPlayer = isPlayer1Turn ? c1 : c2;
            currentPlayer.setInvincible(true);
            System.out.println("Invincible!");

            redScreen = true;
            if (isPlayer1Turn) {
                currentPlayer.setHealth(currentPlayer.getHealth() - 100);
            }
            currentPlayer.setImage(isPlayer1Turn ? hitIc1.getImage() : hitIc2.getImage());
            currentPlayer.setAlpha(80);

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redScreen = false;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (currentPlayer.getImage() == (isPlayer1Turn ? hitIc1.getImage() : hitIc2.getImage())) {
                currentPlayer.setImage(isPlayer1Turn ? cookieIc1.getImage() : cookieIc2.getImage());
            }

            for (int j = 0; j < 11; j++) {
                currentPlayer.setAlpha(currentPlayer.getAlpha() == 80 ? 160 : 80);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            currentPlayer.setAlpha(255);
            currentPlayer.setInvincible(false);
            System.out.println("No longer invincible.");
        }).start();
    }

    private void hitEffectWithoutHealthLoss(Cookie player) {
        new Thread(() -> {
            player.setInvincible(true);
            System.out.println("Invincible!");

            redScreen = true;
            player.setImage(player == c1 ? hitIc1.getImage() : hitIc2.getImage());
            player.setAlpha(80);

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redScreen = false;

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (player.getImage() == (player == c1 ? hitIc1.getImage() : hitIc2.getImage())) {
                player.setImage(player == c1 ? cookieIc1.getImage() : cookieIc2.getImage());
            }

            for (int j = 0; j < 11; j++) {
                player.setAlpha(player.getAlpha() == 80 ? 160 : 80);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            player.setAlpha(255);
            player.setInvincible(false);
            System.out.println("No longer invincible.");
        }).start();
    }

    private void fall() {
        new Thread(() -> {
            while (true) {
                Cookie currentPlayer = isPlayer1Turn ? c1 : c2;
                foot = currentPlayer.getY() + currentPlayer.getHeight();

                if (!escKeyOn && foot < nowField && !currentPlayer.isJump() && !currentPlayer.isFall()) {
                    currentPlayer.setFall(true);
                    System.out.println("Falling");

                    if (currentPlayer.getCountJump() == 2) {
                        currentPlayer.setImage(isPlayer1Turn ? fallIc1.getImage() : fallIc2.getImage());
                    }

                    long t1 = Util.getTime();
                    long t2;
                    int set = 1;

                    while (foot < nowField) {
                        t2 = Util.getTime() - t1;
                        int fallY = set + (int) ((t2) / 40);
                        foot = currentPlayer.getY() + currentPlayer.getHeight();

                        if (foot + fallY >= nowField) {
                            fallY = nowField - foot;
                        }

                        currentPlayer.setY(currentPlayer.getY() + fallY);

                        if (currentPlayer.isJump()) {
                            break;
                        }

                        if (escKeyOn) {
                            long tempT1 = Util.getTime();
                            long tempT2 = 0;
                            while (escKeyOn) {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            tempT2 = Util.getTime() - tempT1;
                            t1 = t1 + tempT2;
                        }

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    currentPlayer.setFall(false);

                    if ((isPlayer1Turn ? downKeyOn1 : downKeyOn2) && !currentPlayer.isJump() && !currentPlayer.isFall() && currentPlayer.getImage() != (isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage())) {
                        currentPlayer.setImage(isPlayer1Turn ? slideIc1.getImage() : slideIc2.getImage());
                    } else if (!(isPlayer1Turn ? downKeyOn1 : downKeyOn2) && !currentPlayer.isJump() && !currentPlayer.isFall() && currentPlayer.getImage() != (isPlayer1Turn ? cookieIc1.getImage() : cookieIc2.getImage())) {
                        currentPlayer.setImage(isPlayer1Turn ? cookieIc1.getImage() : cookieIc2.getImage());
                    }

                    if (!currentPlayer.isJump()) {
                        currentPlayer.setCountJump(0);
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jump1() {
        if (c1.getCountJump() >= 2) return; // 더블 점프 이상 불가
        new Thread(() -> {
            c1.setCountJump(c1.getCountJump() + 1);
            int nowJump = c1.getCountJump();
            c1.setJump(true);

            if (c1.getCountJump() == 1) {
                System.out.println("Jump");
                c1.setImage(jumpIc1.getImage());
            } else if (c1.getCountJump() == 2) {
                System.out.println("Double Jump");
                c1.setImage(doubleJumpIc1.getImage());
            }

            long t1 = Util.getTime();
            long t2;
            int set = 8;
            int jumpY = 1;

            while (jumpY >= 0) {
                t2 = Util.getTime() - t1;
                jumpY = set - (int) ((t2) / 40);
                c1.setY(c1.getY() - jumpY);

                if (nowJump != c1.getCountJump()) {
                    break;
                }

                if (escKeyOn) {
                    long tempT1 = Util.getTime();
                    long tempT2 = 0;
                    while (escKeyOn) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tempT2 = Util.getTime() - tempT1;
                    t1 = t1 + tempT2;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (nowJump == c1.getCountJump()) {
                c1.setJump(false);
            }
        }).start();
    }

    private void jump2() {
        if (c2.getCountJump() >= 2) return; // 더블 점프 이상 불가
        new Thread(() -> {
            c2.setCountJump(c2.getCountJump() + 1);
            int nowJump = c2.getCountJump();
            c2.setJump(true);

            if (c2.getCountJump() == 1) {
                System.out.println("Jump");
                c2.setImage(jumpIc2.getImage());
            } else if (c2.getCountJump() == 2) {
                System.out.println("Double Jump");
                c2.setImage(doubleJumpIc2.getImage());
            }

            long t1 = Util.getTime();
            long t2;
            int set = 8;
            int jumpY = 1;

            while (jumpY >= 0) {
                t2 = Util.getTime() - t1;
                jumpY = set - (int) ((t2) / 40);
                c2.setY(c2.getY() - jumpY);

                if (nowJump != c2.getCountJump()) {
                    break;
                }

                if (escKeyOn) {
                    long tempT1 = Util.getTime();
                    long tempT2 = 0;
                    while (escKeyOn) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    tempT2 = Util.getTime() - tempT1;
                    t1 = t1 + tempT2;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (nowJump == c2.getCountJump()) {
                c2.setJump(false);
            }
        }).start();
    }

    private void handleMapTransition() {
        if (mapLength > mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc4.getImage()) {
            fadeOn = true;

            new Thread(() -> {
                backFadeOut();
                b11 = new Back(backIc4.getImage(), 0, 0, backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));
                b12 = new Back(backIc4.getImage(), backIc4.getImage().getWidth(null), 0, backIc4.getImage().getWidth(null), backIc4.getImage().getHeight(null));
                b21 = new Back(secondBackIc4.getImage(), 0, 0, secondBackIc4.getImage().getWidth(null), secondBackIc4.getImage().getHeight(null));
                b22 = new Back(secondBackIc4.getImage(), secondBackIc4.getImage().getWidth(null), 0, secondBackIc4.getImage().getWidth(null), secondBackIc4.getImage().getHeight(null));
                backFadeIn();
                fadeOn = false;
            }).start();
        } else if (mapLength > mapLengthList.get(1) * 40 + 800 && mapLength < mapLengthList.get(2) * 40 + 800 && b11.getImage() != backIc3.getImage()) {
            fadeOn = true;

            new Thread(() -> {
                backFadeOut();
                b11 = new Back(backIc3.getImage(), 0, 0, backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));
                b12 = new Back(backIc3.getImage(), backIc3.getImage().getWidth(null), 0, backIc3.getImage().getWidth(null), backIc3.getImage().getHeight(null));
                b21 = new Back(secondBackIc3.getImage(), 0, 0, secondBackIc3.getImage().getWidth(null), secondBackIc3.getImage().getHeight(null));
                b22 = new Back(secondBackIc3.getImage(), secondBackIc3.getImage().getWidth(null), 0, secondBackIc3.getImage().getWidth(null), secondBackIc3.getImage().getHeight(null));
                backFadeIn();
                fadeOn = false;
            }).start();
        } else if (mapLength > mapLengthList.get(0) * 40 + 800 && mapLength < mapLengthList.get(1) * 40 + 800 && b11.getImage() != backIc2.getImage()) {
            fadeOn = true;

            new Thread(() -> {
                backFadeOut();
                b11 = new Back(backIc2.getImage(), 0, 0, backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));
                b12 = new Back(backIc2.getImage(), backIc2.getImage().getWidth(null), 0, backIc2.getImage().getWidth(null), backIc2.getImage().getHeight(null));
                b21 = new Back(secondBackIc2.getImage(), 0, 0, secondBackIc2.getImage().getWidth(null), secondBackIc2.getImage().getHeight(null));
                b22 = new Back(secondBackIc2.getImage(), secondBackIc2.getImage().getWidth(null), 0, secondBackIc2.getImage().getWidth(null), secondBackIc2.getImage().getHeight(null));
                backFadeIn();
                fadeOn = false;
            }).start();
        }
    }

    private void backFadeOut() {
        for (int i = 0; i < 256; i += 2) {
            backFade = new Color(0, 0, 0, i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void backFadeIn() {
        for (int i = 255; i >= 0; i -= 2) {
            backFade = new Color(0, 0, 0, i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleMessage(String msg) {
        switch (msg) {
            case "jump":
                if (isPlayer1Turn) {
                    jump1();
                } else {
                    jump2();
                }
                break;
            case "slide_down":
                if (isPlayer1Turn) {
                    downKeyOn1 = true;
                    if (c1.getImage() != slideIc1.getImage() && !c1.isJump() && !c1.isFall()) {
                        c1.setImage(slideIc1.getImage());
                    }
                } else {
                    downKeyOn2 = true;
                    if (c2.getImage() != slideIc2.getImage() && !c2.isJump() && !c2.isFall()) {
                        c2.setImage(slideIc2.getImage());
                    }
                }
                break;
            case "slide_up":
                if (isPlayer1Turn) {
                    downKeyOn1 = false;
                    if (c1.getImage() != cookieIc1.getImage() && !c1.isJump() && !c1.isFall()) {
                        c1.setImage(cookieIc1.getImage());
                    }
                } else {
                    downKeyOn2 = false;
                    if (c2.getImage() != cookieIc2.getImage() && !c2.isJump() && !c2.isFall()) {
                        c2.setImage(cookieIc2.getImage());
                    }
                }
                break;
            case "hit":
                hit();
                break;
            case "endGame":
                endGame();
                break;
            // 추가적인 메시지 핸들링 로직
        }
    }
    private void updateLifeBar(Cookie player) {
        buffg.drawImage(lifeBar.getImage(), 20, 30, null);
        buffg.setColor(Color.BLACK);
        buffg.fillRect(84 + (int) (470 * ((double) player.getHealth() / 1000)), 65, 1 + 470 - (int) (470 * ((double) player.getHealth() / 1000)), 21);
    }

    private void endGame() {
        // Send end game message and result score to server
        sendGameMessage("endGame:" + resultScore); // 스코어 전송

        // Ensure EndPanel is added to the CardLayout container
        Container parent = this.getParent();
        while (!(parent instanceof JPanel)) {
            parent = parent.getParent();
        }
        if (parent != null && parent.getLayout() instanceof CardLayout) {
            CardLayout cardLayout = (CardLayout) parent.getLayout();
            cardLayout.show(parent, "End");
        }
    }
}
