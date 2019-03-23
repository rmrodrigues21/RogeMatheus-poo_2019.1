class Calculadora:
    def __init__(self, bateriaMax):
        self.bateria = 0
        self.bateriaMaxima = bateriaMax
    
    def gastarBateria(self):
        if(self.bateria == 0):
            print("fail: bateria insuficiente")
            return False
        
        self.bateria -= 1
        return True

    def soma(self, a, b):
        if(self.gastarBateria()):
            print(a + b)

    def dividir(self, a, b):
        if(self.gastarBateria()):
            print(a / b)
    

    def charge(self, value):
        self.bateria += value
        if(self.bateria > self.bateriaMaxima):
            self.bateria = self.bateriaMaxima

    def __str__(self):
        return "bateria = " + str(self.bateria) + "/" + str(self.bateriaMaxima)

calc = Calculadora(0)

print("mostrar, inserir _maxCarga, recarregar _value, soma _a _b, dividir _a _b ")
while True:
    ui = raw_input().split(" ")
    if ui[0] == "end":
        break
    elif ui[0] == "inserir":
        calc = Calculadora(int(ui[1]))
    elif ui[0] == "mostrar":
        print(calc)
    elif ui[0] == "recarregar":
        calc.charge(int(ui[1]))
    elif ui[0] == "soma":
        calc.soma(int(ui[1]), int(ui[2]))
    elif ui[0] == "dividir":
        calc.dividir(int(ui[1]), int(ui[2]))    
    else:
        print("comando invalido")

    
