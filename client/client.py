import socket
import tkinter as tk

def imprimir_dados():
    entrada_texto = entrada.get()  # Obtém o texto inserido na entrada
    dados.config(text=entrada_texto)  # Atualiza o rótulo com os dados da entrada

HOST = '127.0.0.1'  # Endereço do host correspondente
PORT = 14000  # Porta de Comunicação entre os sockets

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as socket_client:  # Criação do socket
    socket_client.connect((HOST, PORT))
    data = socket_client.recv(1024)
    #print(data.decode('utf-8'))
    received_data = data.decode('utf-8')

    # Cria a janela principal do cliente
    window = tk.Tk()
    window.title("Dados da turma")

    # Ajustando o tamanho da janela
    window_width = 600
    window_height = 400
    window.geometry(f"{window_width}x{window_height}")

    # Cria um widget Text para exibir os dados com barra de rolagem
    data_text = tk.Text(window, wrap="word", bg="white", fg="black")
    data_text.pack(fill="both", expand=True, padx=10, pady=10)
    data_text.insert("1.0", received_data)

    # Criando uma barra de rolagem
    scrollbar = tk.Scrollbar(data_text)
    scrollbar.pack(side="right", fill="y")
    data_text.config(yscrollcommand=scrollbar.set)
    scrollbar.config(command=data_text.yview)

    #loop principal
    window.mainloop()

socket_client.close()