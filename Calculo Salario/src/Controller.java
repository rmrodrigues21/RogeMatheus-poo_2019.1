import java.util.HashMap;
import java.util.Scanner;

abstract class Funcionario {

    protected String nome;
    public int quantidade_diarias;
    public int maximo_diarias;
    protected double bonus;

    abstract double calculo_salario();
    abstract double adicionar_diaria();
    public Funcionario(String nome,int max_diarias) {

        this.nome = nome;
        this.maximo_diarias = max_diarias;
        this.quantidade_diarias = quantidade_diarias;
        this.bonus = bonus;
    }
    public String toString() {
        return  nome;
    }


    public double setBonus() {
        return this.bonus/3;

    }


}
//criando tipo de funcionario servidor
abstract class Servidor extends Funcionario {

    private int nivel;

    public Servidor(String nome,int nivel) {
        super(nome, 1);
        this.nivel = nivel;
    }
    public String toString() {
        return "Sta:"+  super.toString()+ "\n nivel : " + nivel+ "\n salario : "+ calculo_salario();
    }

    @Override
    public double calculo_salario() {
        return (300 * nivel) + 3000;
    }
    public double setBonus() {
        return calculo_salario()+super.setBonus();
    }

}


//criando o tipo de funcionario   professor
class Professor extends Funcionario {

    char classe;

    public Professor(String nome,char classe) {
        super(nome, 2);
        this.classe = classe;

    }

    public String toString() {
        return "Professor: "+ super.toString() + "\nclasse: " + classe + "\nsalario: "+this.calculo_salario();
    }
    public double calculo_salario() {
        return (classe - 'A')* 2000 + 3000;
    }
    public double adicionar_diaria() {

        return 0;
    }

    public double setBonus() {
        return this.calculo_salario() + super.bonus;
    }

}
//criando  tipo funcionario tercerizado

class Tercerizado extends Funcionario {

    protected int horas_trabalhadas;
    protected boolean insalubre;


    public Tercerizado(String nome, int horas_trabalhadas, boolean insalubre) {
        super(nome, 0);
        this.horas_trabalhadas = horas_trabalhadas;
        this.insalubre = true;
    }
    public String toString() {
        return "Terceirizado: "+super.toString()+ "\n horas trabalhadas : "+ horas_trabalhadas + " \n Insalubre :"+ insalubre + "\n salario: "+ calculo_salario();
    }
    public double calculo_salario() {
        double salario = (4 * horas_trabalhadas );
        if (insalubre == true) {
            salario += 500;
            return salario;
        }
        return salario ;
    }
    @Override
    double adicionar_diaria() {
        System.out.println("Nao possui diarias");
        return calculo_salario();
    }
    public double setBonus() {
        return calculo_salario() + super.setBonus();
    }
}
//controlle



public class Controller {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Funcionario> mapaf = new HashMap<String,Funcionario>();
        while (true){
            String line = sc.nextLine();
            String [] ui = line.split(" ");
            try{
                if (ui[0].equals("end")) {
                    break;
                }else if(ui[0].equals("adicionarProf")){
                    Funcionario fp = new Professor((ui[1]),ui[2].charAt(0));
                    mapaf.put(ui[1], fp);
                }else if(ui[0].equals("adicionarSta")){
                    Funcionario fst = new Servidor((ui[1]), Integer.parseInt(ui[2])){

                        double adicionar_diaria() {
                            return 0;
                        }
                    };
                    mapaf.put(ui[1],fst);
                }else if(ui[0].equals("adicionarTerceirizado")){
                    Funcionario ft = new Tercerizado((ui[1]),Integer.parseInt(ui[2]),Boolean.parseBoolean(ui[3]));
                    mapaf.put(ui[1],ft);
                }else if(ui[0].equals("adicionarDiaria")) {
                    Funcionario fp = mapaf.get(ui[1]);
                    fp.adicionar_diaria();
                }else if (ui[0].equals("adicionarBonus")) {
                    Funcionario fp = mapaf.get(Double.parseDouble(ui[1]));
                    fp.setBonus();
                }
                else if(ui[0].equals("show")) {
                    Funcionario fp = mapaf.get(ui[1]);
                    if(fp != null)
                        System.out.println(fp);
                    else
                        System.out.println("fail: funcionario nao existe");
                }else  if(ui[0].equals("remover")){
                    Funcionario fp = mapaf.remove(ui[1]);
                }else {
                    System.out.println("comando invalido");
                }
            }catch(NullPointerException e) {
                System.out.println("fail: Funcionario nao adicionado");
            }catch(RuntimeException e) {
                System.out.println("fail: Paramentos incorretos");

            }
        }

    }



}

