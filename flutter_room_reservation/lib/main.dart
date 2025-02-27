import 'package:flutter/material.dart';
import 'package:intl/intl.dart'; // Para formateo de fechas

void main() {
  runApp(RoomReservationApp());
}

class RoomReservationApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Reserva de Habitación',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: RoomReservationScreen(),
    );
  }
}

class RoomReservationScreen extends StatefulWidget {
  @override
  _RoomReservationScreenState createState() => _RoomReservationScreenState();
}

class _RoomReservationScreenState extends State<RoomReservationScreen> {
  final TextEditingController _entryDateController = TextEditingController();
  final TextEditingController _exitDateController = TextEditingController();
  final TextEditingController _peopleController = TextEditingController();

  Future<void> _selectDate(BuildContext context, TextEditingController controller) async {
    DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime.now(),
      lastDate: DateTime(2100),
    );

    if (picked != null) {
      setState(() {
        controller.text = DateFormat('yyyy-MM-dd').format(picked);
      });
    }
  }

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

  void _makeReservation() {
    String entryDate = _entryDateController.text.trim();
    String exitDate = _exitDateController.text.trim();
    String people = _peopleController.text.trim();

    if (entryDate.isEmpty || exitDate.isEmpty || people.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
    } else {
      String message = "Fecha de Entrada: $entryDate\n"
          "Fecha de Salida: $exitDate\n"
          "Número de Personas: $people";
      _showDialog("Reserva de Habitación", message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Reserva de Habitación")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Reserva de Habitación",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Fecha de Entrada", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _entryDateController,
              readOnly: true,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Selecciona la fecha",
                suffixIcon: IconButton(
                  icon: Icon(Icons.calendar_today),
                  onPressed: () => _selectDate(context, _entryDateController),
                ),
              ),
            ),
            SizedBox(height: 20),
            Text("Fecha de Salida", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _exitDateController,
              readOnly: true,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Selecciona la fecha",
                suffixIcon: IconButton(
                  icon: Icon(Icons.calendar_today),
                  onPressed: () => _selectDate(context, _exitDateController),
                ),
              ),
            ),
            SizedBox(height: 20),
            Text("Número de Personas", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _peopleController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese el número de personas",
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _makeReservation,
              child: Text("Reservar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}