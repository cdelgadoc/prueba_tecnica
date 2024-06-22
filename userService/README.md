# Crear la imagen
    docker build -t cliente-devsu-img .

# Crear el container
    docker run -p 8000:8000 --name cliente-devsu cliente-devsu-img
