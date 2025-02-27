import 'package:flutter/material.dart';

void main() {
  runApp(LeaveCommentApp());
}

class LeaveCommentApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Dejar un Comentario',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LeaveCommentScreen(),
    );
  }
}

class LeaveCommentScreen extends StatefulWidget {
  @override
  _LeaveCommentScreenState createState() => _LeaveCommentScreenState();
}

class _LeaveCommentScreenState extends State<LeaveCommentScreen> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _commentController = TextEditingController();

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

  void _submitComment() {
    String name = _nameController.text.trim();
    String email = _emailController.text.trim();
    String comment = _commentController.text.trim();

    if (name.isEmpty || email.isEmpty || comment.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
    } else {
      String message = "Nombre: $name\n"
          "Email: $email\n"
          "Comentario:\n$comment";
      _showDialog("Comentario Enviado", message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Dejar un Comentario")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Dejar un Comentario",
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
            Text("Email", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _emailController,
              keyboardType: TextInputType.emailAddress,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese su email",
              ),
            ),
            SizedBox(height: 20),
            Text("Comentario", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _commentController,
              maxLines: 4,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Escriba su comentario aquí...",
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _submitComment,
              child: Text("Enviar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
