import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame implements ActionListener {
    JTextField display;
    JButton[] numberButton;
    JButton[] operatorButton;
    JButton equalsButton, clearButton;
    String myOperators = "";
    double num1 = 0, num2 = 0, result = 0;

    // GUI 부분
    public Calc() {
        // 프레임 레이아웃 설정
    	setTitle("Calculator  1.0.0"); // 이름 설정
    	setSize(400, 500); // 사이즈 설정
    	this.setResizable(false); // 사용자가 사이즈 조정 불가능하게 설정 
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임이 닫힐 때 프로그램도 함께 종료
    	setLayout(new BorderLayout()); // BorderLayout 배치 관리자 사용

    	// 디스플레이 생성
    	display = new JTextField();
    	display.setFont(new Font("Dialog", 1, 30)); // Dialog, 볼드체, 크기 30으로 설정
    	display.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
    	display.setEditable(false); // 사용자가 입력 불가능 하도록 설정
    	add(display, BorderLayout.NORTH); // 추가
    
    	// 버튼 패널 생성
    	JPanel buttonPanel = new JPanel();
    	buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 4행, 4열, 수직-수평 간격 10
    	
    	// 숫자 버튼 생성
    	numberButton = new JButton[10];
    	for(int i = 0; i < 10; i++) {
    		numberButton[i] = new JButton(String.valueOf(i));
    		numberButton[i].addActionListener(this); // Action 이벤트 리스너
    		buttonPanel.add(numberButton[i]);
    	}
    	
    	// 연산 버튼 생성
    	String[] operatorTile = {"+", "-", "*", "/"};
    	operatorButton = new JButton[4];
    	for(int i = 0; i < 4; i++) {
    		operatorButton[i] = new JButton(operatorTile[i]);
    		operatorButton[i].addActionListener(this); // Action 이벤트 리스너 
    		buttonPanel.add(operatorButton[i]);
    	}
    	
    	// =, CE 버튼 생성
    	equalsButton = new JButton("=");
    	equalsButton.addActionListener(this); // Action 이벤트 리스너
    	
    	clearButton = new JButton("CE");
    	clearButton.addActionListener(this); // Action 이벤트 리스너 
    	
    	// 패널에 버튼 추가
    	JPanel panel = new JPanel();
    	panel.setLayout(new GridLayout(1, 2, 10, 10)); // 1행, 2열, 수직-수평 간격 10
    	
    	panel.add(equalsButton); // 패널에 = 추가
    	panel.add(clearButton); // 패널에 CE 추가
    	add(buttonPanel, BorderLayout.CENTER); // 프레임에 버튼 추가
    	add(panel, BorderLayout.SOUTH); // 프레임에 패널 추가
    	
    	setVisible(true);
    }
    
    // 작동 부분
    //	ActionListener 인터페이스를 구현할 때 메소드 이름은 actionPerformed이어야함
    public void actionPerformed(ActionEvent e) {
    	JButton source = (JButton) e.getSource(); // 사용자가 클릭한 버튼 알아내기
        String text = source.getText(); // 버튼 타이틀 텍스트를 읽어 text에 저장
        
        // 0~9가 입력되면
        if(text.charAt(0) >= '0' && text.charAt(0) <='9')
        	display.setText(display.getText() + text); // 디스플레이에 text append       
        // CE가 입력되면 
        else if (text.equals("CE")) {
        	display.setText(""); // 디스플레이 초기화
        	num1 = num2 = result = 0; // num1, num2, result 0으로 초기화
        	myOperators = ""; // myOperators 초기화
        }
        // = 가 입력되면
        else if (text.equals("=")) {
        	num2 = Double.parseDouble(display.getText());
            switch (myOperators) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) // 0으로 나누지 않는 경우 (정상)
                        result = num1 / num2;
                    else {
                        JOptionPane.showMessageDialog(this, "0으로 나눌 수 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                        result = 0;
                    } // 0으로 나누는 경우 오류      
                    break;
            }
            display.setText(String.valueOf(result)); // 답을 출력
            num1 = result; // 답을 num에 저장하여 후속 연산을 위한 준비
            myOperators = ""; // 연산자 초기화
        }
        else {
        	myOperators = text;
            num1 = Double.parseDouble(display.getText()); 
            display.setText(""); // num1이 입력되면 디스플레이를 초기화
        }
    }

    public static void main(String[] args) {
        new Calc();
    }
}
