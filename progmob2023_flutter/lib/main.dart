import 'dart:convert';
import 'package:progmob2023_flutter/constants.dart';
import 'package:progmob2023_flutter/details_screen.dart';
import 'package:progmob2023_flutter/details_user.dart';
import 'package:progmob2023_flutter/schools.dart';
import 'package:progmob2023_flutter/model/category.dart';
import 'package:flutter/material.dart';
import 'package:flutter_staggered_grid_view/flutter_staggered_grid_view.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:progmob2023_flutter/schools.dart';
import 'package:progmob2023_flutter/tutor_delete.dart';
import 'package:progmob2023_flutter/tutor_fetch.dart';
import 'package:progmob2023_flutter/tutor_send.dart';
import 'package:progmob2023_flutter/tutor_update.dart';
import 'package:sidebarx/sidebarx.dart';
import 'package:http/http.dart' as http;

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Course App',
      theme: ThemeData(),
      home: HomeScreen(),
      routes: {
        '/detailsuser': (context) => DetailsUser(),
        '/schools': (context) => Schools()
      },
    );
  }
}

class HomeScreen extends StatelessWidget {
  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: Padding(
        padding: EdgeInsets.only(left: 20, top: 50, right: 20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                InkWell(
                  onTap: () {
                    if (_scaffoldKey.currentState != null) {
                      _scaffoldKey.currentState!.openDrawer();
                    }
                  },
                  child: SvgPicture.asset("assets/icons/menu.svg"),
                ),
                InkWell(
                  onTap: () {
                    // Navigator.push(
                    //   context,
                    //   MaterialPageRoute(
                    //     builder: (context) => DetailsUser(),
                    //   ),
                    // );
                    Navigator.pushNamed(
                        context, "/detailsuser"); //Pakai route di main.dart
                    // Navigator.pushReplacement( //untuk end atau kill tampilan dan pindah tampilan terawal ke replacement
                    //   context,
                    //   MaterialPageRoute<void>(
                    //       builder: (BuildContext context) => DetailsUser(),
                    //   )
                    // );
                  },
                  child: Image.asset("assets/images/user.png"),
                )
              ],
            ),
            SizedBox(height: 30),
            Text("Hey Jeevallucas Gautama,", style: kHeadingextStyle),
            Text("Find a course you want to learn", style: kSubheadingextStyle),
            Container(
              margin: EdgeInsets.symmetric(vertical: 30),
              padding: EdgeInsets.symmetric(horizontal: 20, vertical: 16),
              height: 60,
              width: double.infinity,
              decoration: BoxDecoration(
                color: Color(0xFFF5F5F7),
                borderRadius: BorderRadius.circular(40),
              ),
              child: Row(
                children: <Widget>[
                  SvgPicture.asset("assets/icons/search.svg"),
                  SizedBox(width: 16),
                  Text(
                    "Search for anything",
                    style: TextStyle(
                      fontSize: 18,
                      color: Color(0xFFA0A5BD),
                    ),
                  )
                ],
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: <Widget>[
                Text("Category", style: kTitleTextStyle),
                Text(
                  "See All",
                  style: kSubtitleTextSyule.copyWith(color: kBlueColor),
                ),
              ],
            ),
            SizedBox(height: 15),
            Expanded(
              child: MasonryGridView.count(
                padding: EdgeInsets.all(0),
                crossAxisCount: 2,
                itemCount: categories.length,
                crossAxisSpacing: 20,
                mainAxisSpacing: 20,
                itemBuilder: (context, index) {
                  return GestureDetector(
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => DetailsScreen(),
                        ),
                      );
                    },
                    child: Container(
                      padding: EdgeInsets.all(20),
                      height: index.isEven ? 200 : 240,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(16),
                        image: DecorationImage(
                          image: AssetImage(categories[index].image),
                          fit: BoxFit.fill,
                        ),
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Text(
                            categories[index].name,
                            style: kTitleTextStyle,
                          ),
                          Text(
                            '${categories[index].numOfCourses} Courses',
                            style: TextStyle(
                              color: kTextColor.withOpacity(.5),
                            ),
                          )
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
      drawer: Drawer(
        // Add a ListView to the drawer. This ensures the user can scroll
        // through the options in the drawer if there isn't enough vertical
        // space to fit everything.
        child: ListView(
          // Important: Remove any padding from the ListView.
          padding: EdgeInsets.zero,
          children: [
            // const DrawerHeader(
            //   decoration: BoxDecoration(
            //     color: Colors.blue,
            //   ),
            //   child: Text('Drawer Header'),
            // ),
            UserAccountsDrawerHeader(
                // Pertemuan 10
                currentAccountPicture: CircleAvatar(
                    backgroundColor: Colors.white,
                    child:
                        // Text("NP", style: TextStyle(fontSize: 36)),
                        Image.asset("assets/images/user.png",
                            width: 70, height: 70, fit: BoxFit.cover)),
                accountName: Text("Jeevallucas Gautama"),
                accountEmail: Text("jnanamaitriya@gmail.com")),
            ListTile(
              title: const Text('Home'),
              trailing: Icon(Icons.home), //Pertemuan 10
              // leading: Icon(Icons.home), //Pertemuan 10
              subtitle: Text("Kembali ke halaman utama"), //Pertemuan 10
              onTap: () {
                // Then close the drawer
                Navigator.pop(context);
              },
            ),
            ListTile(
              title: const Text('Business'),
              trailing: Icon(Icons.business), //Pertemuan 10
              // leading: Icon(Icons.home), //Pertemuan 10
              subtitle: Text("Tentang bisnis kami"), //Pertemuan 10
              // selected: _selectedIndex == 1,
              onTap: () {
                // Update the state of the app
                // _onItemTapped(1);
                // Then close the drawer
                Navigator.pop(context);
              },
            ),
            ListTile(
              title: const Text('School'),
              trailing: Icon(Icons.school), //Pertemuan 10
              // leading: Icon(Icons.home), //Pertemuan 10
              subtitle: Text("Kerjasama kami dengan sekolah"), //Pertemuan 10
              // selected: _selectedIndex == 2,
              onTap: () {
                // Update the state of the app
                // _onItemTapped(2);
                // Then close the drawer
                Navigator.pop(
                    context); // Berfungsi untuk menutup side menu setelah berpindah
                Navigator.pushNamed(context, "/schools");
              },
            ),
            ListTile(
              title: const Text('Fetch Data'),
              trailing: Icon(Icons.school_sharp),
              subtitle: Text("Tutorial Fetch Data"),
              // selected: _selectedIndex == 2,
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => TutorFetch(),
                  ),
                );
              },
            ),
            ListTile(
              title: const Text('Send Data'),
              trailing: Icon(Icons.school_sharp),
              subtitle: Text("Tutorial Send Data"),
              // selected: _selectedIndex == 2,
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => TutorSend(),
                  ),
                );
              },
            ),
            ListTile(
              title: const Text('Update Data'),
              trailing: Icon(Icons.school_sharp),
              subtitle: Text("Tutorial Update Data"),
              // selected: _selectedIndex == 2,
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => TutorUpdate(),
                  ),
                );
              },
            ),
            ListTile(
              title: const Text('Delete Data'),
              trailing: Icon(Icons.school_sharp),
              subtitle: Text("Tutorial Delete Data"),
              // selected: _selectedIndex == 2,
              onTap: () {
                Navigator.pop(context);
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => TutorDelete(),
                  ),
                );
              },
            ),
            Divider(
              //Pertemuan 10
              color: Colors.black54,
              // endIndent: 20, // Garis Berakhir Pada
              // indent: 20, // Garis Bermula Pada
            ),
          ],
        ),
      ),
    );
  }
}
