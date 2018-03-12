package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DAO;
import model.UsuarioModel;

/**
 *
 */

public class EditarUsuarioController {
    UsuarioModel usuario;
    @FXML
    TextField nombre;
    @FXML
    TextField apellidos;
    @FXML
    TextField dni;
    @FXML
    TextField Telefono;
    @FXML
    TextField Direccion;
    @FXML
    TextField email;
    @FXML
    TextField password;
    @FXML
    TextField repassword;



    InicioSesionController inicioSesionController;

    InicioController inicioController;
    @FXML
    public void comprobar(){
        String fallo ="";
        int error=0;
        int Tel = 0;
        if (nombre.getText().equals("")){
            fallo = fallo+"\n Campo nombre: vacio";
            error++;
        }
        if (apellidos.getText().equals("")){
            fallo = fallo+"\n Campo apellidos: vacio";
            error++;
        }
        if (dni.getText().equals("")){
            fallo = fallo+"\n Campo DNI: vacio";
            error++;
        }
        if (Telefono.getText().equals("")){
            fallo = fallo+"\n Campo telefono: vacio";
            error++;
        }else {
            try{
                Tel = Integer.parseInt(Telefono.getText());
            }catch (Exception e){
                fallo = fallo+"\n Campo telefono: Solo pueden ser numeros";
                error++;
            }
            if (Telefono.getText().length()>9 || Telefono.getText().length()<9){
                fallo = fallo+"\n Campo telefono: Solo pueden tener 9 numeros";
                error++;
            }
        }
        if (Direccion.getText().equals("")){
            fallo = fallo+"\n Campo direccion: vacio";
            error++;
        }
        if (nombre.getText().equals("")){
            fallo = fallo+"\n Campo email: vacio";
            error++;
        }
        if (password.getText().equals("")){
            fallo = fallo+"\n Campo contraseña: vacio";
            error++;
        }else{
            if (!password.getText().equals(repassword.getText())){
                fallo = fallo+"\n Las contraseñas no coinciden";
                error++;
            }
        }
        if (error>0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error en el registro");
            alert.setHeaderText("Se encontraron los siguientes problemas:");
            alert.setContentText(fallo);
            alert.showAndWait();
        }else{
            DAO dao = new DAO();

            usuario.setNombre(nombre.getText());
            usuario.setApellidos(apellidos.getText());
            usuario.setDNI(dni.getText());
            usuario.setDireccion(Direccion.getText());
            usuario.setTelefono(Tel);
            usuario.setPassword(password.getText());
            usuario.setEmail(email.getText());

            dao.editarPersona(usuario);
            Stage stage1= (Stage) nombre.getScene().getWindow();
            stage1.close();
        }
    }

    public void datos(UsuarioModel usuario){
        this.usuario=usuario;
        this.nombre.setText(usuario.getNombre());
        this.apellidos.setText(usuario.getApellidos());
        this.dni.setText(usuario.getDNI());
        this.Telefono.setText(String.valueOf(usuario.getTelefono()));
        this.Direccion.setText(usuario.getDireccion());
        this.email.setText(usuario.getEmail());


    }

}
