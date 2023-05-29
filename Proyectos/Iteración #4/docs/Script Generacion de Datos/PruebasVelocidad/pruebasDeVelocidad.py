import csv
import time

def consultar_servicios():
    alojamiento_file = '../Alojamiento.csv'
    servicio_file = '../AlojamientoServicio.csv'
    servicio_id = 2
    
    registros_servicio = []
    with open(servicio_file, 'r') as file:
        reader = csv.reader(file)
        next(reader)  
        for row in reader:
            if int(row[1]) == servicio_id:
                registros_servicio.append(row[0])

    resultados = []
    with open(alojamiento_file, 'r') as file:
        reader = csv.reader(file)
        next(reader)  
        for row in reader:
            if row[0] in registros_servicio:
                resultados.append((row[0], row[6]))
    return resultados

start_time = time.time()

resultados = consultar_servicios()

elapsed_time = time.time() - start_time

for resultado in resultados:
    print(f'ID: {resultado[0]}, idServicio: {resultado[1]}')

print(f'Tiempo transcurrido: {elapsed_time} segundos')

