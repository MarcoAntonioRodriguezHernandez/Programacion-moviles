import 'package:flutter/material.dart';

void main() {
  runApp(PayWithCardApp());
}

class PayWithCardApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Pago con Tarjeta',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: PayWithCardScreen(),
    );
  }
}

class PayWithCardScreen extends StatefulWidget {
  @override
  _PayWithCardScreenState createState() => _PayWithCardScreenState();
}

class _PayWithCardScreenState extends State<PayWithCardScreen> {
  final TextEditingController _cardNumberController = TextEditingController();
  final TextEditingController _expiryDateController = TextEditingController();
  final TextEditingController _cvvController = TextEditingController();

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

  void _processPayment() {
    String cardNumber = _cardNumberController.text.trim();
    String expiryDate = _expiryDateController.text.trim();
    String cvv = _cvvController.text.trim();

    if (cardNumber.isEmpty || expiryDate.isEmpty || cvv.isEmpty) {
      _showDialog("Error", "Por favor, completa todos los campos.");
      return;
    }

    if (cardNumber.length != 16 || !RegExp(r'^[0-9]+$').hasMatch(cardNumber)) {
      _showDialog("Error", "El número de tarjeta debe tener 16 dígitos numéricos.");
      return;
    }

    if (!RegExp(r'^\d{2}/\d{2}$').hasMatch(expiryDate)) {
      _showDialog("Error", "Formato de fecha inválido. Usa MM/YY.");
      return;
    }

    if (cvv.length < 3 || cvv.length > 4 || !RegExp(r'^[0-9]+$').hasMatch(cvv)) {
      _showDialog("Error", "El CVV debe tener 3 o 4 dígitos numéricos.");
      return;
    }

    String maskedCard = "**** **** **** ${cardNumber.substring(cardNumber.length - 4)}";

    String message = "Número de Tarjeta: $maskedCard\n"
        "Fecha de Expiración: $expiryDate\n"
        "CVV: ***";

    _showDialog("Pago Realizado", message);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Pago con Tarjeta")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Pago con Tarjeta",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Número de Tarjeta", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _cardNumberController,
              keyboardType: TextInputType.number,
              maxLength: 16,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese los 16 dígitos",
              ),
            ),
            SizedBox(height: 20),
            Text("Fecha de Expiración (MM/YY)", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _expiryDateController,
              keyboardType: TextInputType.datetime,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "MM/YY",
              ),
            ),
            SizedBox(height: 20),
            Text("CVV", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _cvvController,
              keyboardType: TextInputType.number,
              maxLength: 4,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "3 o 4 dígitos",
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _processPayment,
              child: Text("Pagar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
