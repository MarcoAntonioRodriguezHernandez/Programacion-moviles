import 'package:flutter/material.dart';

void main() {
  runApp(ContactFormApp());
}

class ContactFormApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Formulario de Contacto',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ContactFormScreen(),
    );
  }
}

class ContactFormScreen extends StatefulWidget {
  @override
  _ContactFormScreenState createState() => _ContactFormScreenState();
}

class _ContactFormScreenState extends State<ContactFormScreen> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _phoneController = TextEditingController();
  final TextEditingController _messageController = TextEditingController();

  void _showDialog(String title, String message) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(title),
          content: Text(message),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text("Aceptar"),
            ),
          ],
        );
      },
    );
  }

  void _submitForm() {
    String name = _nameController.text.trim();
    String phone = _phoneController.text.trim();
    String message = _messageController.text.trim();

    if (name.isEmpty || phone.isEmpty || message.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
      return;
    }

    if (phone.length < 7 || !RegExp(r'^[0-9]+$').hasMatch(phone)) {
      _showDialog("Error", "El número de teléfono debe tener al menos 7 dígitos numéricos.");
      return;
    }

    String finalMessage = "Nombre: $name\n"
        "Teléfono: $phone\n"
        "Mensaje:\n$message";

    _showDialog("Mensaje Enviado", finalMessage);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Formulario de Contacto")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Formulario de Contacto",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Nombre", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _nameController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese su nombre",
              ),
            ),
            SizedBox(height: 20),
            Text("Teléfono", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _phoneController,
              keyboardType: TextInputType.phone,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese su teléfono",
              ),
            ),
            SizedBox(height: 20),
            Text("Mensaje", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _messageController,
              maxLines: 4,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Escriba su mensaje aquí...",
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _submitForm,
              child: Text("Enviar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
