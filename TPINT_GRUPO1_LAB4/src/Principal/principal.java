package Principal;




import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class principal {

    public static void main(String[] args) {
        // Datos del correo
        String destinatario = "fernandopalacios51@gmail.com"; // Cambia por el correo del destinatario
        String asunto = "Bienvenido a nuestro sistema";
        String mensaje = "Hola, gracias por registrarte. ¡Bienvenido!";

        // Enviar correo
        enviarCorreo(destinatario, asunto, mensaje);
    }

    public static void enviarCorreo(String destinatario, String asunto, String mensaje) {
        // Configuración del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP
        props.put("mail.smtp.port", "587"); // Puerto SMTP
        props.put("mail.smtp.auth", "true"); // Autenticación
        props.put("mail.smtp.starttls.enable", "true"); // Conexión segura

        // Credenciales del remitente
        final String remitente = "programadoressomos404@gmail.com"; // Cambiar por tu correo
        final String password = "opwlysytyzcqgagv"; // Cambiar por tu contraseña

        // Crear la sesión
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            // Enviar el mensaje
            Transport.send(message);
            System.out.println("Correo enviado exitosamente a " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
