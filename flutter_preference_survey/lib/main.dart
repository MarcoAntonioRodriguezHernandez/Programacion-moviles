import 'package:flutter/material.dart';

void main() {
  runApp(PreferenceSurveyApp());
}

class PreferenceSurveyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Encuesta de Preferencias',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: PreferenceSurveyScreen(),
    );
  }
}

class PreferenceSurveyScreen extends StatefulWidget {
  @override
  _PreferenceSurveyScreenState createState() => _PreferenceSurveyScreenState();
}

class _PreferenceSurveyScreenState extends State<PreferenceSurveyScreen> {
  final TextEditingController _colorController = TextEditingController();
  final TextEditingController _foodController = TextEditingController();

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
    String color = _colorController.text.trim();
    String food = _foodController.text.trim();

    if (color.isEmpty || food.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
    } else {
      String message = "Color Favorito: $color\n"
          "Comida Favorita: $food";
      _showDialog("Encuesta Enviada", message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Encuesta de Preferencias")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Encuesta de Preferencias",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Color Favorito", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _colorController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese su color favorito",
              ),
            ),
            SizedBox(height: 20),
            Text("Comida Favorita", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _foodController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese su comida favorita",
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
