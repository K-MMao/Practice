import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

class Execution{
    public static void main(String[]args){
        Janken janken = new Janken();
        janken.execution();
    }
}

class Janken{
    private Map<Integer, String>hands = new HashMap<Integer, String>();//じゃんけんの手
    private List<String>results = new ArrayList<String>();//勝敗の結果表示
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int playerHand;
    private int cpuHand;
    private int win = 0;
    private int lose = 0;
    private int drow = 0;

    public Janken(){
        hands.put(1, "グー");
        hands.put(2, "チョキ");
        hands.put(3, "パー");

        results.add("あいこ");
        results.add("勝ち");
        results.add("負け");
    }

    public void execution(){
        this.battle();
    }

    public void showSelect(){
        hands.forEach((key, value) ->{
            System.out.print(key + ":" + value + " ");
        });
    }

    public int inputNum(){
        try{
            return Integer.parseInt(br.readLine());
        } catch(NumberFormatException | IOException e){
            return 0;
        }
    }

    public void select(){
        while(true){
            this.showSelect();
            System.out.println();
            this.playerHand = this.inputNum();
            if(this.playerHand > 0 && this.playerHand <= 3){
                break;
            } else {
                System.out.println("該当する半角数字で入力してください");
            }
        }
    }
    
    public void selectCpu(){
        Random rnd = new Random();
        this.cpuHand = rnd.nextInt(3) + 1;
    }
    
    public void check(){
        String Player = "";
        String CPU = "";
        String Result = "";
        switch(this.playerHand){
            case 1:
                Player = "グー";
                if(this.cpuHand == 1){
                    CPU = "グー";
                    Result = this.results.get(0);
                    this.drow++;
                } else if(this.cpuHand == 2){
                    CPU = "チョキ";
                    Result = this.results.get(1);
                    this.win++;
                } else if (this.cpuHand == 3){
                    CPU = "パー";
                    Result = this.results.get(2);
                    this.lose++;
                }
                break;
            case 2:
                Player = "チョキ";
                if(this.cpuHand == 1){
                    CPU = "グー";
                    Result = this.results.get(2);
                    this.lose++;
                } else if (this.cpuHand == 2){
                    CPU = "チョキ";
                    Result = this.results.get(0);
                    this.drow++;
                } else if(this.cpuHand == 3){
                    CPU = "パー";
                    Result = this.results.get(1);
                    this.win++;
                }
                break;
            case 3:
                Player = "パー";
                if(this.cpuHand == 1){
                    CPU = "グー";
                    Result = this.results.get(1);
                    this.win++;
                } else if (this.cpuHand == 2){
                    CPU = "チョキ";
                    Result = this.results.get(2);
                    this.lose++;
                } else if(this.cpuHand == 3){
                    CPU = "パー";
                    Result = this.results.get(0);
                    this.drow++;
                }
                break;
        }
        System.out.println("Player : " + Player);
        System.out.println("CPU    : " + CPU);
        System.out.println("Result : " + Result);
    }
    public void battle(){
        boolean finish = true;
        while(finish){
            this.select();
            this.selectCpu();
            this.check();
            while(true){
                System.out.print("1:もう一度対戦する");
                System.out.println("2:終了する");
                int select = this.inputNum();
                if(select == 1){
                    break;
                } else if(select == 2){
                    System.out.println("最終結果 : " + this.win + "勝 " + this.lose + "敗 " + this.drow + "引き分け");
                    finish = false;
                    break;
                } else {
                    System.out.println("該当する半角数字で入力してください");
                }
            }
            
        }
    } 
}

