# Crear la imagen
    docker build -t banco-devsu-img .

# Crear el container
    docker run -p 9000:9000 --name banco-devsu banco-devsu-img
