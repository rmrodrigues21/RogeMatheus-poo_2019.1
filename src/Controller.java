import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Cliente{
    String id;
    String NomeCompleto;
    float saldo;
    public Cliente(String id, String NomeCompleto){
        this.id = id;
        this.NomeCompleto = NomeCompleto;
        this.saldo = 0;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.NomeCompleto + ":" + this.saldo;
    }
}

class Sistema{
    float saldo;
    ArrayList<Cliente> clientes;

    public Sistema(float saldo){
        this.saldo = saldo;
        this.clientes = new ArrayList<Cliente>();

    }

    void cadastrar(Cliente cliente){
        if(this.findCliente(cliente.id) != null){
            System.out.println("Falha: id ja existe!");
            return;
        }
        clientes.add(cliente);
    }
    Cliente findCliente(String id){
        for(Cliente cli : clientes) {
            if(cli.id.equals(id))
                return cli;
        }
        return null;
    }
    void emprestar (String id, float saldo){
        Cliente cli = findCliente(id);
        if(cli == null){
            System.out.println("Falha: Cliente nao existe!");
            return;
        }
        this.saldo -= saldo;
        cli.saldo += saldo;
    }

    @Override
    public String toString() {
        String saida = "";
        for(Cliente cliente : clientes)
            saida += cliente +"\n";
        saida += "saldo:" + this.saldo;
        return saida;
    }
}

public class Controller {
    public static void main(String[] args) {
        Sistema sistema = new Sistema(0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");

            if (ui[0].equals("Sair")) {
                break;
            }else if (ui[0].equals("Iniciar")) {
                sistema = new Sistema(Float.parseFloat(ui[1]));

            }else if (ui[0].equals("Mostrar")){
                System.out.println(sistema);

            }else if (ui[0].equals("Emprestar")){
                sistema.emprestar(ui[1], Float.parseFloat(ui[2]));

            }else if (ui[0].equals("Cadastrar")){
                String id = ui[1];


                String[] subarray = Arrays.copyOfRange(ui, 2, ui.length);
                String NomeCompleto = String.join(" ", subarray);
                sistema.cadastrar(new Cliente(id, NomeCompleto));

            }else{
                System.out.println("Falha: Comando Invalido");

            }
        }
    }
}