# Etapes pour lancer le projet

- **Installer gradle**
    ```bash
    sudo apt install gradle -y
    ```

- **Installer python 3 sur votre machine**
    ```bash
    sudo apt install python3 python3-pip
    ```


- **Installer Matplotlib**
    ```bash
    pip install matplotlib numpy
    ```


- **Compilation**
    ```bash
    gradle clean build
    ```

- **Lancement**
    ```bash
    gradle run (expérimentations)
    gradle run -PmainClass=view.Main (visualisation)
    ```
