import 'package:flutter/material.dart';

void main() {
  runApp(SatisfactionSurveyApp());
}

class SatisfactionSurveyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Encuesta de Satisfacción',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: SurveyScreen(),
    );
  }
}

class SurveyScreen extends StatefulWidget {
  @override
  _SurveyScreenState createState() => _SurveyScreenState();
}

class _SurveyScreenState extends State<SurveyScreen> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _commentsController = TextEditingController();
  double _rating = 0;

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

  void _submitSurvey() {
    String name = _nameController.text.trim();
    String comments = _commentsController.text.trim();

    if (name.isEmpty || _rating == 0 || comments.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos antes de enviar.");
    } else {
      String message = "Nombre: $name\n"
          "Nivel de Satisfacción: $_rating estrellas\n"
          "Comentarios: $comments";

      _showDialog("Encuesta Enviada", message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Encuesta de Satisfacción")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Encuesta de Satisfacción",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Nombre", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _nameController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingresa tu nombre",
              ),
            ),
            SizedBox(height: 20),
            Text("Nivel de Satisfacción", style: TextStyle(fontSize: 18)),
            SizedBox(height: 10),
            Slider(
              value: _rating,
              onChanged: (newRating) {
                setState(() {
                  _rating = newRating;
                });
              },
              min: 0,
              max: 5,
              divisions: 5,
              label: _rating.toString(),
            ),
            SizedBox(height: 20),
            Text("Comentarios", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _commentsController,
              maxLines: 4,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Escribe tus comentarios aquí...",
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _submitSurvey,
              child: Text("Enviar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
