import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Transacao {
    int id;
    float valor;
    String clienteId;

    public Transacao (int id, float valor, String clienteId) {
        this.id = id ;
        this.valor = valor ;
        this.clienteId = clienteId ;

    }

    public String toString() {
        return " " + id + ":" + valor + ":" + clienteId;

    }
}

class Cliente{
    String id;
    String nomecompleto;
    float saldo;
    public Cliente (String id, String nomecompleto) {
        this.id = id;
        this.nomecompleto = nomecompleto;
        this.saldo = 0;
    }

    @Override
    public String toString() {
        return this.id + ":" + this.nomecompleto + ":" + this.saldo;

    }
}

class Sistema {
    float saldo;
    ArrayList<Cliente> clientes;
    ArrayList<Transacao> transacoes;
    int nextTrId;

    public Sistema (float saldo){
        this.saldo = saldo;
        this.clientes = new ArrayList<Cliente>();
        this.transacoes = new ArrayList<Transacao>() ;
        this.nextTrId = 0;
    }

    void cadastrar(Cliente cliente) {
        try {
            this.findCliente(cliente.id);
            throw new RuntimeException("Cliente ja existe");
        }catch(RuntimeException re){
            clientes.add(cliente);
        }
    }
    Cliente findCliente (String id) {
        for (Cliente cli : clientes) {
            if (cli.id.equals(id))
                return cli;
        }
        throw new RuntimeException ("fail: Cliente nao existe");
    }

    void addTransacao(float valor, String clienteId) {
        this.transacoes.add(new Transacao(nextTrId, valor, clienteId));
        nextTrId += 1;
    }

    void emprestar(String id, float saldo) {
        Cliente cli = findCliente(id);
        addTransacao(- saldo, id);
        this.saldo -= saldo;
        cli.saldo += saldo;
    }

    void receber (String id, float saldo) {
        Cliente cli = findCliente(id);
        if(cli.saldo < saldo) {
            System.out.println("Falha: Pagamento maior que a divida");
            return ;
        }
        addTransacao(saldo, id);
        cli.saldo -= saldo;
        this.saldo += saldo;
    }
    ArrayList<Transacao> getHistorico() {
        return transacoes;
    }

    @Override
    public String toString() {
        String saida = " ";
        for(Cliente cliente : clientes)
            saida += cliente + "\n";
        saida += "saldo: " + this.saldo;
        return saida;
    }


}

public class Controller2 {
    public static void main (String[] args){
        Sistema sistema = new Sistema(0);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            try {
                if (ui[0].equals("end")) {
                    break;
                } else if (ui[0].equals("init")) {
                    sistema = new Sistema(Float.parseFloat(ui[1]));
                } else if (ui[0].equals("show")) {
                    System.out.println(sistema);
                } else if (ui[0].equals("emprestar")) {
                    sistema.emprestar(ui[1], Float.parseFloat(ui[2]));
                } else if (ui[0].equals("historico")) {
                    for (Transacao tr : sistema.getHistorico())
                        System.out.println(tr);
                } else if (ui[0].equals("cadastrar")) {
                    String id = ui[1];
                    /*

                    String nomecompleto = " ";
                    for (int i = 2; i < ui.length; i++) {
                        nomecompleto += ui[i] + " ";
                    }
                    nomecompleto = nomecompleto.substring(0, nomecompleto.length() -1);
                    */

                    String[] subarray = Arrays.copyOfRange(ui, 2, ui.length);
                    String nomecompleto = String.join(" ", subarray);
                    sistema.cadastrar(new Cliente(id, nomecompleto));

                } else {
                    System.out.println("Falha: Comando invalido");

                }

            } catch (RuntimeException re) {
                System.out.println(re.getMessage());
            }
        }
    }
}
