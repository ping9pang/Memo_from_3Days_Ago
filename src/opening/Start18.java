package opening;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start18 extends JFrame {
    private boolean isTriangleVisible = true;  // 삼각형 깜빡임 상태
    private Timer blinkTimer;

    public Start18() {
        setTitle("Start18");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(new Start18.MyPanel());
        getContentPane().setBackground(Color.BLACK);
        getContentPane().setLayout(null);

        setSize(1440, 1024);
        setVisible(true);

        BlinkingButton();// 삼각형 깜빡이기 시작


        // 키보드 리스너 추가
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE) {
                    goToNextPage(); // 엔터 또는 스페이스바를 누르면 다음 페이지로 이동
                }
            }
        });

        setFocusable(true); // 키보드 입력을 받을 수 있도록 설정
        requestFocusInWindow(); // 윈도우가 포커스를 가지도록 설정
    }
    public void BlinkingButton() {

        // 500ms마다 상태 변경하는 타이머 설정
        blinkTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 삼각형 깜빡임
                isTriangleVisible = !isTriangleVisible;
                repaint();  // 화면 다시 그리기
            }
        });
        blinkTimer.start();  // 타이머 시작
    }

    // 버튼에 대해 삼각형 그리기
    class MyPanel extends JPanel {
        private ImageIcon profileIcon = new ImageIcon("images/characters/프_지훈.png");
        private Image profileImage = profileIcon.getImage();

        public MyPanel() {
            setLayout(null); // 레이아웃 설정

            // 캐릭터 이름 라벨
            JLabel nameLabel = new JLabel("명지훈");
            nameLabel.setBounds(200, 390, 300, 50);
            nameLabel.setFont(new Font("Inter", Font.BOLD, 48));
            nameLabel.setForeground(Color.WHITE);

            // 캐릭터 프로필 아이콘
            JLabel profileLabel = new JLabel(profileIcon);
            profileLabel.setBounds(45, 340, 130, 134);

            // 대화 내용 텍스트
            JTextArea textArea = new JTextArea("당시엔 이 메모장이 당연히 장난일 것이라고 생각했다. 하지만 적힌 날짜, 옥상에서 밀어버릴 거라고 적혀있는 글씨. 이건 분명 이번 추락사와 일치한다. 성하가 떨어진 이 사건을 말하는 것이다.");
            textArea.setBounds(50, 530, 1180, 150);
            textArea.setFont(new Font("Inter", Font.PLAIN, 30));
            textArea.setForeground(Color.white);
            textArea.setEditable(false); // 편집 불가능
            textArea.setOpaque(false); // 배경을 투명하게 설정
            textArea.setLineWrap(true); // 줄 바꿈 허용
            textArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈


            // 삼각형 클릭 이벤트 추가
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();

                    // 삼각형 영역 확인
                    if (isTriangleClicked(mouseX, mouseY)) {
                        goToNextPage();
                    }
                }
            });

            // 컴포넌트 추가
            add(nameLabel);
            add(profileLabel);
            add(textArea);

        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(30, 500, 1220, 245);

            if (isTriangleVisible) {
                // 삼각형 그리기
                int[] xPoints = {1190, 1190, 1215};
                int[] yPoints = {680, 710, 695};
                int nPoints = 3;

                g.setColor(Color.GRAY);
                g.fillPolygon(xPoints, yPoints, nPoints);

            }
        }

        private boolean isTriangleClicked(int x, int y) {
            int[] xPoints = {1190, 1190, 1215};
            int[] yPoints = {680, 710, 695};

            Polygon triangle = new Polygon(xPoints, yPoints, 3);
            return triangle.contains(x, y);
        }
    }

    private void goToNextPage() {
        Start18.this.dispose(); // 현재 창 닫기
        new Start19();
    }

    public static void main(String[] args) {
        new Start18();
    }
}

