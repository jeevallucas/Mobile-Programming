import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_map_location_marker/flutter_map_location_marker.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter_map/flutter_map.dart';
import 'package:latlong2/latlong.dart';
import 'package:location/location.dart';
import 'package:maps_launcher/maps_launcher.dart';
import 'package:sliding_up_panel/sliding_up_panel.dart';

class MyMap extends StatefulWidget {
  @override
  _MyMapState createState() => _MyMapState();
}

class _MyMapState extends State<MyMap> {
  LocationData? currentLocation;
  Location location = Location();

  late FollowOnLocationUpdate _followOnLocationUpdate;
  late StreamController<double?> _followCurrentLocationStreamController;

  // Dataset
  final String data1 =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
      "Cras sodales ac est id consequat. "
      "Maecenas massa odio, pulvinar at suscipit a, facilisis nec lorem.";
  final String data2 =
      "Proin ultrices semper felis, sed ultricies nibh ultrices facilisis. "
      "Cras convallis, nisl eu molestie sollicitudin, elit libero semper turpis, porttitor interdum arcu ipsum ut felis. "
      "Nunc auctor ante malesuada, porttitor risus in, varius nisi. ";
  final String data3 =
      "Mauris euismod, turpis id tristique tincidunt, ligula sem tempor justo, viverra euismod justo mauris ut neque. "
      "Quisque facilisis a erat ac suscipit. ";
  final String data4 =
      "Fusce auctor fringilla nulla vel dignissim. "
      "Mauris ut iaculis leo. ";
  late String dataSlide;
  late double long;
  late double lat;
  List<Marker> allMarkers = [];

  @override
  void initState() {
    super.initState();

    // inisialisasi awal
    _getLocation();
    _followOnLocationUpdate = FollowOnLocationUpdate.always;
    _followCurrentLocationStreamController = StreamController<double?>();

    //  init slidingup data
    dataSlide = data1;

    //  inisialisasi marker
    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.772883015693774, 110.35162041008356),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data1;
              lat = -7.772883015693774;
              long = 110.35162041008356;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.776925950151403, 110.417587608133),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data2;
              lat = -7.776925950151403;
              long = 110.417587608133;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.810447112334517, 110.3317282395377),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data3;
              lat = -7.810447112334517;
              long = 110.3317282395377;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );

    allMarkers.add(
      Marker(
        width: 100.0,
        height: 100.0,
        point: const LatLng(-7.814152804539516, 110.39514515931207),
        // Example location 1
        child: IconButton(
          onPressed: () {
            setState(() {
              dataSlide = data4;
              lat = -7.814152804539516;
              long = 110.39514515931207;
            });
          },
          icon: const Icon(Icons.pin_drop, color: Colors.black),
        ), // Customize the marker
      ),
    );
  }

  Future<void> _getLocation() async {
    try {
      currentLocation = await location.getLocation();
      setState(() {});
    } catch (e) {
      if (kDebugMode) {
        print('Error getting location: $e');
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          if (currentLocation != null)
            FlutterMap(
              options: MapOptions(
                initialCenter: LatLng(
                  currentLocation!.latitude ?? 0.0,
                  currentLocation!.longitude ?? 0.0,
                ),
                initialZoom: 10.0,
              ),
              children: [
                TileLayer(
                  urlTemplate: 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                  subdomains: const ['a', 'b', 'c'],
                  tileSize: 256,
                ),
                CurrentLocationLayer(
                  followOnLocationUpdate: _followOnLocationUpdate,
                ),
                MarkerLayer(markers: allMarkers)
              ],
            ),
          SlidingUpPanel(
            backdropEnabled: true,
            panel: Column(
              children: [
                Text(dataSlide),
                ElevatedButton(
                  onPressed: () {
                    MapsLauncher.launchCoordinates(lat, long);
                  },
                  child: Text("Navigasi"),
                ),
              ],
            ),
          ),
          Positioned(
            bottom: 16,
            right: 16,
            child: FloatingActionButton(
              onPressed: () {
                setState(
                  () => _followOnLocationUpdate = FollowOnLocationUpdate.always,
                );
              },
              child: const Icon(
                Icons.my_location,
                color: Colors.white,
              ),
            ),
          ),
          if (currentLocation == null)
            const Center(
              child: CircularProgressIndicator(),
            ),
        ],
      ),
    );
  }
}
