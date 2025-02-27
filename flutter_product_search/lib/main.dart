import 'package:flutter/material.dart';

void main() {
  runApp(ProductSearchApp());
}

class ProductSearchApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Búsqueda de Producto',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: ProductSearchScreen(),
    );
  }
}

class ProductSearchScreen extends StatefulWidget {
  @override
  _ProductSearchScreenState createState() => _ProductSearchScreenState();
}

class _ProductSearchScreenState extends State<ProductSearchScreen> {
  final TextEditingController _productController = TextEditingController();
  String _selectedCategory = "Electrónica";
  final List<String> _categories = [
    "Electrónica", "Ropa", "Hogar", "Deportes", "Alimentos", "Otros"
  ];

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

  void _searchProduct() {
    String product = _productController.text.trim();

    if (product.isEmpty) {
      _showDialog("Error", "Por favor, ingrese un nombre de producto.");
    } else {
      String message = "Producto: $product\nCategoría: $_selectedCategory";
      _showDialog("Búsqueda de Producto", message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Búsqueda de Producto")),
      body: Padding(
        padding: EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              "Buscar Producto",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),
            SizedBox(height: 20),
            Text("Nombre del producto", style: TextStyle(fontSize: 18)),
            TextField(
              controller: _productController,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                hintText: "Ingrese el nombre del producto",
              ),
            ),
            SizedBox(height: 20),
            Text("Categoría", style: TextStyle(fontSize: 18)),
            DropdownButtonFormField<String>(
              value: _selectedCategory,
              items: _categories.map((String category) {
                return DropdownMenuItem<String>(
                  value: category,
                  child: Text(category),
                );
              }).toList(),
              onChanged: (newValue) {
                setState(() {
                  _selectedCategory = newValue!;
                });
              },
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                contentPadding: EdgeInsets.symmetric(horizontal: 10),
              ),
            ),
            SizedBox(height: 20),
            ElevatedButton(
              onPressed: _searchProduct,
              child: Text("Buscar", style: TextStyle(fontSize: 20)),
            ),
          ],
        ),
      ),
    );
  }
}
