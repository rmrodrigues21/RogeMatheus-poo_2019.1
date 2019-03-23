class Carro:
    def __init__(self):
        
        self.passageiros = 0
        self.kilometros = 0
        self.gasolina = 0
        self.limite_passageiros = 2
        self.limite_gasolina = 10
        
    def __str__ (self):
        return "Passageiros: " + str(self.passageiros) + ", Gasolina " + str (self.gasolina) + ", kilometros: " + str(self.kilometros)

    def Entrar (self):
        if self.passageiros < self.limite_passageiros:
            self.passageiros += 1
        else:
            print ("Falha: limite de passageiros atingido!")

    def Sair (self):
        if self.passageiros > 0:
            self.passageiros -= 1
        else:
            print ("Falha: nao tem passageiros no carro!")

    def Abastecer(self, Quantidade):
        self.gasolina += Quantidade
        if(self.gasolina > self.limite_gasolina):
            self.gasolina = self.limite_gasolina
            
    def Dirigir (self, distancia):
        if self.passageiros == 0:
            print ("Nao tem gente no carro!")
            return
        
        gasolina_necessaria = distancia / 10
        if (self.gasolina >= gasolina_necessaria):
            self.kilometros += distancia
            self.gasolina -= gasolina_necessaria
        else:
            print("Falha: Gasolina Insuficiente!")
        
        
            
carro = Carro()
line = ""
print ("Digite mostrar, entrar, sair, gasolina _quantidade, Dirigir _distancia, end")
while (line != "end"):
    line = raw_input()
    eu = line.split (" ")
           
    if eu [0] == "end":
        break
    elif eu [0] == "mostrar":
        print (carro)
    elif eu [0] == "entrar":
        carro.Entrar()
    elif eu [0] == "sair":
        carro.Sair()
    elif eu [0] == "gasolina":
           carro.Abastecer(int(eu[1]))
    elif eu [0] == "Dirigir":
           carro.Dirigir(int(eu[1]))
    else:
        print ("Falha: Comando Invalido")

        
    
            
            
