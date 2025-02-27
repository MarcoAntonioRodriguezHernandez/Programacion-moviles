import 'package:flutter/material.dart';

void main() {
  runApp(EventInscriptionApp());
}

class EventInscriptionApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Inscripción a Evento',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: EventInscriptionScreen(),
    );
  }
}

class EventInscriptionScreen extends StatefulWidget {
  @override
  _EventInscriptionScreenState createState() => _EventInscriptionScreenState();
}

class _EventInscriptionScreenState extends State<EventInscriptionScreen> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  String? _attendance; // "si" o "no"

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

  void _submitInscription() {
    String name = _nameController.text.trim();
    String email = _emailController.text.trim();

    if (name.isEmpty || email.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
      return;
    }

    if (_attendance == null) {
      _showDialog("Error", "Por favor, selecciona si asistirás al evento.");
      return;
    }

    String attendanceText = _attendance == "si" ? "Sí, asistirá al evento." : "No asistirá al evento.";

    String message = "Nombre: $name\n"
        "Email: $email\n"
        "Asistencia: $attendanceText";

    _showDialog("Inscripción Completa", message);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Inscripción a Evento")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Inscripción a Evento",
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
            Text("¿Asistirá al evento?", style: TextStyle(fontSize: 18)),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Radio<String>(
                  value: "si",
                  groupValue: _attendance,
                  onChanged: (String? value) {
                    setState(() {
                      _attendance = value;
                    });
                  },
                ),
                Text("Sí"),
                SizedBox(width: 20),
                Radio<String>(
                  value: "no",
                  groupValue: _attendance,
                  onChanged: (String? value) {
                    setState(() {
                      _attendance = value;
                    });
                  },
                ),
                Text("No"),
              ],
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _submitInscription,
              child: Text("Inscribirse", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
