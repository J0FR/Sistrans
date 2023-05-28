import csv
import random
import names
import sys
import time

alojamientoDuenio = {}
alojamientoCosto = {}

class Main: 
    def generarDatos(self, cantidad: int) -> None:
        cantidadOperadorUsuario = round(cantidad * 0.05)
        cantidadHabitacionHuesped = round(cantidad * 0.05)
        cantidadApartamentoAlquiler = round(cantidad * 0.05)
        cantidadViviendaTemporal = round(cantidad * 0.05)
        cantidadHotel = round(cantidad * 0.02)
        cantidadAdmViviendaUniversitaria = round(cantidad * 0.02)
        cantidadHostal = round(cantidad * 0.02)
        cantidadHabitacionHotel = round(cantidad * 0.04)
        cantidadHacionVivUniversitaria = round(cantidad * 0.04)
        cantidadHabitacionHostal = round(cantidad * 0.04)
        cantidadAlojamiento = round(cantidad * 0.27)
        cantidadAlojamientoServicio = round(cantidad * 0.1)
        cantidadCliente = round(cantidad * 0.1)
        cantidadReserva = round(cantidad * 0.15)
        
        # Instanciamiento de clases
        operadorUsuario = OperadorUsuario()
        cliente = Cliente()
        hotel = Hotel()
        admviviendaUniversitaria = AdmViviendaUniversitaria()
        hostal = Hostal()
        habitacionHuesped = HabitacionHuesped(1, cantidadOperadorUsuario)
        apartamentoAlquiler = ApartamentoAlquiler(cantidadHabitacionHuesped + 1, cantidadOperadorUsuario)
        viviendaTemporal = ViviendaTemporal(cantidadHabitacionHuesped + cantidadApartamentoAlquiler + 1, cantidadOperadorUsuario)
        habitacionHotel = HabitacionHotel(cantidadHabitacionHuesped + cantidadApartamentoAlquiler + cantidadViviendaTemporal + 1, cantidadHotel)
        habitacionViviendaUniversitaria = HabitacionViviendaUniversitaria(cantidadHabitacionHuesped + cantidadApartamentoAlquiler + cantidadViviendaTemporal + cantidadHabitacionHotel + 1, cantidadAdmViviendaUniversitaria)
        habitacionhostal = HabitacionHostal(cantidadHabitacionHuesped + cantidadApartamentoAlquiler + cantidadViviendaTemporal + cantidadHabitacionHotel + cantidadHacionVivUniversitaria + 1, cantidadHostal)
        alojamiento = Alojamiento(cantidad)
        alojamientoServicio = AlojamientoServicio(cantidadHabitacionHuesped + cantidadApartamentoAlquiler + cantidadViviendaTemporal + 1, cantidadHabitacionHuesped + cantidadApartamentoAlquiler + cantidadViviendaTemporal + cantidadHabitacionHotel + cantidadHacionVivUniversitaria + 1)
        reserva = Reserva(cantidadCliente, cantidadAlojamiento)
        
        print(f"habitacionHuesped - {sys.getsizeof(habitacionHuesped)}")
        print(f"apartamentoAlquiler - {sys.getsizeof(apartamentoAlquiler)}")
        print(f"viviendaTemporal - {sys.getsizeof(viviendaTemporal)}")
        print(f"habitacionHotel - {sys.getsizeof(habitacionHotel)}")
        print(f"habitacionViviendaUniversitaria - {sys.getsizeof(habitacionViviendaUniversitaria)}")
        print(f"habitacionhostal - {sys.getsizeof(habitacionhostal)}")
        
        # Usuarios
        print("Generando usuarios...")
        operadorUsuario.generarOperadorUsuario(cantidadOperadorUsuario)
        print("Generando clientes...")
        cliente.generarCliente(cantidadCliente)
        print("Generando operadores hoteles...")
        hotel.generarHotel(cantidadHotel)
        print("Generando operadores vivienda universitaria...")
        admviviendaUniversitaria.generarAdmViviendaUniversitaria(cantidadAdmViviendaUniversitaria)
        print("Generando operadores hostal...")
        hostal.generarHostal(cantidadHostal)
        # Alojamientos
        print("Generando habitacion huesped...")
        habitacionHuesped.generarHabitacionHuesped(cantidadHabitacionHuesped)
        print("Generando apartamento alquiler...")
        apartamentoAlquiler.generarApartamentoAlquiler(cantidadApartamentoAlquiler)
        print("Generando vivienda temporal...")
        viviendaTemporal.generarViviendaTemporal(cantidadViviendaTemporal)
        print("Generando habitacion hotel...")
        habitacionHotel.generarHabitacionHotel(cantidadHabitacionHotel)
        print("Generando vivienda universitaria...")
        habitacionViviendaUniversitaria.generarHabitacionViviendaUniversitaria(cantidadHacionVivUniversitaria)
        print("Generando habitacion hostal...")
        habitacionhostal.generarHabitacionHostal(cantidadHabitacionHostal)
        print("Generando alojamiento...")
        alojamiento.generarAlojamiento(cantidadAlojamiento)
        # Extras
        print("Generando alojamiento servicio...")
        alojamientoServicio.generarAlojamientoServicio(cantidadAlojamientoServicio)
        print("Generando reserva...")
        reserva.generarReserva(cantidadReserva)

class OperadorUsuario:
    def __init__(self) -> None:
        self.identificacion = 0
        self.tipoVinculo = ["profesor", "empleado", "egresado", "estudiante", "padre de estudiante"]
        
    def getIdentificacion(self):
        return self.identificacion
    
    def setIdentificacion(self, identificacion: int):
        self.identificacion = identificacion
        
    def getTipoVinculo(self):
        return self.tipoVinculo
    
    def getRandomOperadorUsuario(self) -> list:
        identificacion = self.getIdentificacion() + 1
        self.setIdentificacion(identificacion)
        nombre = names.get_full_name()
        vinculo = random.choice(self.getTipoVinculo())
        correoElectronico = names.get_first_name().lower() + "@uniandes.edu.co"
        telefono = random.randint(100000000,9999999999)
        return [identificacion, nombre, vinculo, correoElectronico, telefono]
    
    def generarOperadorUsuario(self, cantidad: int):
        with open("operadorUsuario.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                operadorUsuario = self.getRandomOperadorUsuario()
                writer.writerow([operadorUsuario[0], operadorUsuario[1], operadorUsuario[2], operadorUsuario[3], operadorUsuario[4]])
        file.close()
        

class HabitacionHuesped:
    def __init__(self, idAlojamiento, cantidadOperadorUsuario) -> None:
        self.idAlojamiento = idAlojamiento
        self.tipoBanio = ["privado", "compartido"]
        self.tipoHabitacionHuesped = ["individual", "compartido"]
        self.cantidadOperadorUsuario = cantidadOperadorUsuario
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getTipoBanio(self):
        return self.tipoBanio
    
    def getTipoHabitacionHuesped(self):
        return self.tipoHabitacionHuesped
    
    def getCantidadOperadorUsuario(self):
        return self.cantidadOperadorUsuario
        
    def getRandomHabitacionHuesped(self) -> list:
        idAlojamiento = self.getIdAlojamiento() 
        self.setIdAlojamiento(idAlojamiento + 1)
        comidas = random.randint(0,3)
        tipoBanio = random.choice(self.getTipoBanio())
        tipoHabitacion = random.choice(self.getTipoHabitacionHuesped())
        dtoMesExtra = random.randint(1,100)
        identificacionOperadorUsuario = random.randint(1, self.getCantidadOperadorUsuario())
        return [idAlojamiento, comidas, tipoBanio, tipoHabitacion, dtoMesExtra, identificacionOperadorUsuario]
    
    def generarHabitacionHuesped(self, cantidad: int):
        with open("habitacionHuesped.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                habitacionHuesped = self.getRandomHabitacionHuesped()
                alojamientoDuenio[habitacionHuesped[0]] = habitacionHuesped[5]
                writer.writerow([habitacionHuesped[0], habitacionHuesped[1], habitacionHuesped[2], habitacionHuesped[3], habitacionHuesped[4], habitacionHuesped[5]])
        file.close()


class ApartamentoAlquiler:
    def __init__(self, idAlojamiento, cantidadOperadorUsuario) -> None:
        self.idAlojamiento = idAlojamiento
        self.cantidadOperadorUsuario = cantidadOperadorUsuario
        self.boolean = ["Y", "N"]
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getCantidadOperadorUsuario(self):
        return self.cantidadOperadorUsuario
        
    def getBoolean(self):
        return self.boolean
        
    def getRandomApartamentoAlquiler(self) -> list:
        idAlojamiento = self.getIdAlojamiento()
        self.setIdAlojamiento(idAlojamiento + 1)
        servPublico = random.choice(self.getBoolean())
        administracion = random.choice(self.getBoolean())
        identificacionOperadorUsuario = random.randint(1, self.getCantidadOperadorUsuario())
        return [idAlojamiento, servPublico, administracion, identificacionOperadorUsuario]
    
    def generarApartamentoAlquiler(self, cantidad: int):
        with open("apartamentoAlquiler.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                apartamentoAlquiler = self.getRandomApartamentoAlquiler()
                alojamientoDuenio[apartamentoAlquiler[0]] = apartamentoAlquiler[3]
                writer.writerow([apartamentoAlquiler[0], apartamentoAlquiler[1], apartamentoAlquiler[2], apartamentoAlquiler[3]])
        file.close()
        

class ViviendaTemporal:
    def __init__(self, idAlojamiento, cantidadOperadorUsuario) -> None:
        self.idAlojamiento = idAlojamiento
        self.cantidadOperadorUsuario = cantidadOperadorUsuario
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getCantidadOperadorUsuario(self):
        return self.cantidadOperadorUsuario
        
    def getRandomViviendaTemporal(self) -> list:
        idAlojamiento = self.getIdAlojamiento()
        self.setIdAlojamiento(idAlojamiento + 1)
        numeroHabitaciones = random.randint(1,10)
        precioSeguroArrendamiento = random.randint(200,1000)
        caracteristicasSeguro = "Seguro contra incendios, inundaciones, terremotos, robo, etc."
        diasAlquilado = random.randint(1,15)
        identificacionOperadorUsuario = random.randint(1, self.getCantidadOperadorUsuario())
        return [idAlojamiento, numeroHabitaciones, precioSeguroArrendamiento, caracteristicasSeguro, diasAlquilado, identificacionOperadorUsuario]
    
    def generarViviendaTemporal(self, cantidad: int):
        with open("viviendaTemporal.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                viviendaTemporal = self.getRandomViviendaTemporal()
                alojamientoDuenio[viviendaTemporal[0]] = viviendaTemporal[5]
                writer.writerow([viviendaTemporal[0], viviendaTemporal[1], viviendaTemporal[2], viviendaTemporal[3], viviendaTemporal[4], viviendaTemporal[5]])
        file.close()
        

class Hotel:
    def __init__(self) -> None:
        self.regComercio = 0
        self.boolean = ["Y", "N"]
        
    def getRegComercio(self):
        return self.regComercio
    
    def setRegComercio(self, regComercio: int):
        self.regComercio = regComercio
        
    def getBoolean(self):
        return self.boolean
        
    def getRandomHotel(self) -> list:
        regComercio = self.getRegComercio() + 1
        self.setRegComercio(regComercio)
        nit = regComercio
        nombre = names.get_first_name() + "'s Hotel"
        restaurante = random.choice(self.getBoolean())
        parqueadero = random.choice(self.getBoolean())
        piscina = random.choice(self.getBoolean())
        return [regComercio, nit, nombre, restaurante, parqueadero, piscina]
    
    def generarHotel(self, cantidad: int):
        with open("hotel.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                hotel = self.getRandomHotel()
                writer.writerow([hotel[0], hotel[1], hotel[2], hotel[3], hotel[4], hotel[5]])
        file.close()
        
        
class AdmViviendaUniversitaria:
    def __init__(self) -> None:
        self.regComercio = 0
        self.boolean = ["Y", "N"]
        
    def getRegComercio(self):
        return self.regComercio
    
    def setRegComercio(self, regComercio: int):
        self.regComercio = regComercio
        
    def getBoolean(self):
        return self.boolean
        
    def getRandomAdmViviendaUniversitaria(self) -> list:
        regComercio = self.getRegComercio() + 1
        self.setRegComercio(regComercio)
        nit = regComercio
        nombre = names.get_first_name() + "'s Vivienda Universitaria"
        precioSalaEstudio = random.randint(100,500)
        precioSalaEsparcimiento = random.randint(100,500)
        precioGimnasio = random.randint(100,1000)
        restaurante = random.choice(self.getBoolean())
        return [regComercio, nit, nombre, precioSalaEstudio, precioSalaEsparcimiento, precioGimnasio, restaurante]
    
    def generarAdmViviendaUniversitaria(self, cantidad: int):
        with open("admViviendaUniversitaria.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                admViviendaUniversitaria = self.getRandomAdmViviendaUniversitaria()
                writer.writerow([admViviendaUniversitaria[0], admViviendaUniversitaria[1], admViviendaUniversitaria[2], admViviendaUniversitaria[3], admViviendaUniversitaria[4], admViviendaUniversitaria[5], admViviendaUniversitaria[6]])
        file.close()
        
        
class Hostal:
    def __init__(self) -> None:
        self.regComercio = 0
        
    def getRegComercio(self):
        return self.regComercio
    
    def setRegComercio(self, regComercio: int):
        self.regComercio = regComercio
        
    def getRandomHostal(self) -> list:
        regComercio = self.getRegComercio() + 1
        self.setRegComercio(regComercio)
        nit = regComercio
        nombre = names.get_first_name() + "'s Hostal"
        horaAperturaRecepcion = "8 A.M."
        horacierreRecepcion = "5 P.M."
        
        return [regComercio, nit, nombre, horaAperturaRecepcion, horacierreRecepcion]
    
    def generarHostal(self, cantidad: int):
        with open("hostal.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                hostal = self.getRandomHostal()
                writer.writerow([hostal[0], hostal[1], hostal[2], hostal[3], hostal[4]])
        file.close()
        

class Cliente:
    def __init__(self) -> None:
        self.identificacion = 0
        self.tipoVinculoCliente = ["profesor", "empleado", "egresado", "estudiante", "padre de estudiante", "profesor invitado", "persona evento uniandes"]
        
    def getIdentificacion(self):
        return self.identificacion
    
    def setIdentificacion(self, identificacion: int):
        self.identificacion = identificacion
        
    def getTipoVinculoCliente(self):
        return self.tipoVinculoCliente
        
    def getRandomCliente(self) -> list:
        identificacion = self.getIdentificacion() + 1
        self.setIdentificacion(identificacion)
        nombre = names.get_full_name()
        vinculo = random.choice(self.getTipoVinculoCliente())
        correoElectronico = names.get_first_name().lower() + "@uniandes.edu.co"
        telefono = random.randint(100000000,9999999999)
        ultimaFechaReserva = "NULL"
        saldo = 0
        return [identificacion, nombre, vinculo, correoElectronico, telefono, ultimaFechaReserva, saldo]
    
    def generarCliente(self, cantidad: int):
        with open("cliente.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                cliente = self.getRandomCliente()
                writer.writerow([cliente[0], cliente[1], cliente[2], cliente[3], cliente[4], cliente[5], cliente[6]])
        file.close()
        

class HabitacionHotel:
    def __init__(self, idAlojamiento, cantiadHotel) -> None:
        self.idAlojamiento = idAlojamiento
        self.cantiadHotel = cantiadHotel
        self.tipoHabitacion = ["estandar", "semisuite", "suite"]
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getCantidadHotel(self):
        return self.cantiadHotel
        
    def getTipoHabitacion(self):
        return self.tipoHabitacion
        
    def getRandomHabitacionHotel(self) -> list:
        idAlojamiento = self.getIdAlojamiento() 
        self.setIdAlojamiento(idAlojamiento + 1)
        tipoHabitacion = random.choice(self.getTipoHabitacion())
        tamanio = "Grande"
        idHotel = random.randint(1, self.getCantidadHotel())
        return [idAlojamiento, tipoHabitacion, tamanio, idHotel]
    
    def generarHabitacionHotel(self, cantidad: int):
        with open("habitacionHotel.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                habitacionHotel = self.getRandomHabitacionHotel()
                alojamientoDuenio[habitacionHotel[0]] = habitacionHotel[3]
                writer.writerow([habitacionHotel[0], habitacionHotel[1], habitacionHotel[2], habitacionHotel[3]])
        file.close()
        
        
class HabitacionViviendaUniversitaria:
    def __init__(self, idAlojamiento, cantiadAdmViviendaUniversitaria) -> None:
        self.idAlojamiento = idAlojamiento
        self.cantiadAdmViviendaUniversitaria = cantiadAdmViviendaUniversitaria
        self.tipoHabitacion = ["individual","compartido"]
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getCantidadAdmViviendaUniversitaria(self):
        return self.cantiadAdmViviendaUniversitaria
        
    def getTipoHabitacion(self):
        return self.tipoHabitacion
        
    def getRandomHabitacionViviendaUniversitaria(self) -> list:
        idAlojamiento = self.getIdAlojamiento() 
        self.setIdAlojamiento(idAlojamiento + 1)
        tipoHabitacion = random.choice(self.getTipoHabitacion())
        capacidad = random.randint(1, 4)
        idViviendaUniversitaria = random.randint(1, self.getCantidadAdmViviendaUniversitaria())
        return [idAlojamiento, tipoHabitacion, capacidad, idViviendaUniversitaria]
    
    def generarHabitacionViviendaUniversitaria(self, cantidad: int):
        with open("habitacionViviendaUniversitaria.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                habitacionViviendaUniversitaria = self.getRandomHabitacionViviendaUniversitaria()
                alojamientoDuenio[habitacionViviendaUniversitaria[0]] = habitacionViviendaUniversitaria[3]
                writer.writerow([habitacionViviendaUniversitaria[0], habitacionViviendaUniversitaria[1], habitacionViviendaUniversitaria[2], habitacionViviendaUniversitaria[3]])
        file.close()
        
        
class HabitacionHostal:
    def __init__(self, idAlojamiento, cantiadHostal) -> None:
        self.idAlojamiento = idAlojamiento
        self.cantiadHostal = cantiadHostal
        
    def getIdAlojamiento(self):
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento: int):
        self.idAlojamiento = idAlojamiento
        
    def getCantidadHostal(self):
        return self.cantiadHostal
        
    def getRandomHabitacionHostal(self) -> list:
        idAlojamiento = self.getIdAlojamiento() 
        self.setIdAlojamiento(idAlojamiento + 1)
        aforo = random.randint(1, 10)
        idHostal = random.randint(1, self.getCantidadHostal())
        return [idAlojamiento, aforo, idHostal]
    
    def generarHabitacionHostal(self, cantidad: int) -> None:
        with open("habitacionHostal.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                habitacionHostal = self.getRandomHabitacionHostal()
                alojamientoDuenio[habitacionHostal[0]] = habitacionHostal[2]
                writer.writerow([habitacionHostal[0], habitacionHostal[1], habitacionHostal[2]])
        file.close()
        

class Alojamiento:
    def __init__(self, cantidadRegistros) -> None:
        self.cantidadRegistros = cantidadRegistros
        self.estatus = ["Y", "N"]
        self.tipoAlojamiento = ["HabitacionHuesped", "ApartamentoAlquiler", "ViviendaTemporal", "HabitacionHotel", "HabitacionViviendaUniversitaria", "HabitacionHostal"]
        self.idAlojamiento = 1
        
    def getCantidadRegistros(self) -> int:
        return self.cantidadRegistros
    
    def getEstatus(self) -> list:
        return self.estatus
    
    def getTipoAlojamiento(self) -> list:
        return self.tipoAlojamiento
    
    def getIdAlojamiento(self) -> int:
        return self.idAlojamiento
    
    def setIdAlojamiento(self, idAlojamiento) -> None:
        self.idAlojamiento = idAlojamiento
        
    def getRandomAlojamiento(self) -> list:
        id = self.getIdAlojamiento()
        ubicacion = "Bogota D.C."
        duracionMin = random.randint(1, 30)
        costo = random.randint(10000, 100000)
        estatus = random.choice(self.getEstatus())
        if (round(self.getCantidadRegistros() * 0.05) >= self.getIdAlojamiento()):
            tipoAlojamiento = "HabitacionHuesped"
        elif (round(self.getCantidadRegistros() * 0.10) >= self.getIdAlojamiento()):
            tipoAlojamiento = "ApartamentoAlquiler"
        elif (round(self.getCantidadRegistros() * 0.15) >= self.getIdAlojamiento()):
            tipoAlojamiento = "ViviendaTemporal"
        elif (round(self.getCantidadRegistros() * 0.19) >= self.getIdAlojamiento()):
            tipoAlojamiento = "HabitacionHotel"
        elif (round(self.getCantidadRegistros() * 0.23) >= self.getIdAlojamiento()):
            tipoAlojamiento = "HabitacionViviendaUniversitaria"
        else:
            tipoAlojamiento = "HabitacionHostal"
        idDuenio = alojamientoDuenio[id]
        self.setIdAlojamiento(id + 1)
        return [id, ubicacion, duracionMin, costo, estatus, tipoAlojamiento, idDuenio, idDuenio]
    
    def generarAlojamiento(self, cantidad: int):
        with open("alojamiento.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                alojamiento = self.getRandomAlojamiento()
                alojamientoCosto[alojamiento[0]] = alojamiento[3]
                writer.writerow([alojamiento[0], alojamiento[1], alojamiento[2], alojamiento[3], alojamiento[4], alojamiento[5], alojamiento[6]])
        file.close()
        
        
class AlojamientoServicio:
    def __init__(self, primerAlojamiento: int, ultimoAlojamiento: int) -> None:
        self.primerAlojamiento = primerAlojamiento
        self.ultimoAlojamiento = ultimoAlojamiento
        
    def getPrimerAlojamiento(self) -> int:
        return self.primerAlojamiento
    
    def getUltimoAlojamiento(self) -> int:
        return self.ultimoAlojamiento
        
    def getRandomAlojamientoServicio(self) -> list:
        alojamiento =  random.randint(self.getPrimerAlojamiento(), self.getUltimoAlojamiento())
        servicio = random.randint(1, 13)
        costo = random.randint(100, 1000)
        return [alojamiento, servicio, costo]
    
    def generarAlojamientoServicio(self, cantidad: int) -> None:
        with open("alojamientoServicio.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                alojamientoServicio = self.getRandomAlojamientoServicio()
                writer.writerow([alojamientoServicio[0], alojamientoServicio[1], alojamientoServicio[2]])
        file.close()
        
        
class Reserva:
    def __init__(self, cantidadCliente: int, cantidadAlojamientos: int) -> None:
        self.id = 1
        self.cantidadCliente = cantidadCliente
        self.cantidadAlojamientos = cantidadAlojamientos
        
    def getId(self) -> int:
        return self.id
    
    def setId(self, id) -> None:
        self.id = id
        
    def getCantidadCliente(self) -> int:
        return self.cantidadCliente

    def getCantidadAlojamientos(self) -> int:
        return self.cantidadAlojamientos
        
    def str_time_prop(self, start, end, time_format, prop):
        stime = time.mktime(time.strptime(start, time_format))
        etime = time.mktime(time.strptime(end, time_format))

        ptime = stime + prop * (etime - stime)

        return time.strftime(time_format, time.localtime(ptime))
            
    def random_date(self, start, end, prop):
        return self.str_time_prop(start, end, '%d/%m/%Y', prop)
        
    def getRandomReserva(self) -> list:
        id = self.getId()
        self.setId(id + 1)
        fechaIni = self.random_date("1/1/2022", "1/7/2022", random.random())
        fechaFin = self.random_date("2/7/2022", "29/3/2023", random.random())
        identificacionCliente = random.randint(1, self.getCantidadCliente())
        idAlojamiento = random.randint(1, self.getCantidadAlojamientos())
        estado = "Y"
        idGrupo = id
        ganancia = alojamientoCosto[idAlojamiento]
        numOcupamiento = random.randint(1, 10)
        return [id, fechaIni, fechaFin, identificacionCliente, idAlojamiento, estado, idGrupo, ganancia, numOcupamiento]
    
    def generarReserva(self, cantidad: int) -> None:
        with open("reserva.csv", "w", newline="") as file:
            writer = csv.writer(file)
            for i in range(cantidad):  
                reserva = self.getRandomReserva()
                writer.writerow([reserva[0], reserva[1], reserva[2], reserva[3], reserva[4], reserva[5], reserva[6], reserva[7], reserva[8]])
        file.close()
        
        
main = Main()
main.generarDatos(1000000)