var originalImage;
var grayImage;
var redImage;
var rainbowImage;
var blurImage;
var canvas;

function upload(){
  var file = document.getElementById("inputImg");
  originalImage = new SimpleImage(file);
  grayImage = new SimpleImage(file);
  redImage = new SimpleImage(file);
  rainbowImage = new SimpleImage(file);
  blurImage = new SimpleImage(file);
  canvas = document.getElementById("can");
  originalImage.drawTo(canvas);
}

function isImageLoaded(image){
  if (image !== null || image.complete()){
    return true;
  }
  else{
    alert("The image is not loaded");
    return false;
  }
}

function grayFilter() {
  for (var pixel of grayImage.values()) {
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    pixel.setRed(avg);
    pixel.setGreen(avg);
    pixel.setBlue(avg);
}
}

function redFilter(){
  for (var pixel of redImage.values()){
    var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    if (avg < 128) {
      pixel.setRed(2*avg);
      pixel.setGreen(0);
      pixel.setBlue(0);
    }
    else{
      pixel.setRed(255);
      pixel.setGreen(2*avg-255);
      pixel.setBlue(2*avg-255);
    }
  }
}


function rainbowFilter(){
  var h = rainbowImage.getHeight();
  for (var pixel of rainbowImage.values()) {
    var y = pixel.getY();
    var r = pixel.getRed();
    var g = pixel.getGreen();
    var b = pixel.getBlue();
    var avg = (r+g+b) / 3;
    if (y < h/7) {
      if (avg <128){
        pixel.setRed(2*avg);
        pixel.setGreen(0);
        pixel.setBlue(0);
      }
      else {
        pixel.setRed(255);
        pixel.setGreen(2*avg-255);
        pixel.setBlue(2*avg-255);
      }
    }
    if (y >= h/7 && y < 2*h/7) {
      if (avg <128) {
        pixel.setRed(2*avg);
        pixel.setGreen(0.8*avg);
        pixel.setBlue(0);
      }
      else{
        pixel.setRed(255);
        pixel.setGreen(1.2*avg-51);
        pixel.setBlue(2*avg-255);
      }
    }
    if (y >=2*h/7 && y<3*h/7) {
      if (avg < 128) {
        pixel.setRed(2*avg);
        pixel.setGreen(2*avg);
        pixel.setBlue(0);
      }
      else{
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(2*avg-255);
      }
    }
    if (y>=3*h/7 && y<4*h/7) {
      if (avg < 128) {
        pixel.setRed(0);
        pixel.setGreen(2*avg);
        pixel.setBlue(0);
      }
      else{
        pixel.setRed(2*avg-255);
        pixel.setGreen(255);
        pixel.setBlue(2*avg-255);
      }
    }
    if (y>=4*h/7 && y<5*h/7) {
      if (avg < 128) {
        pixel.setRed(0);
        pixel.setGreen(0);
        pixel.setBlue(2*avg);
      }
      else{
        pixel.setRed(2*avg-255);
        pixel.setGreen(2*avg-255);
        pixel.setBlue(255);
      }
    }
    if (y>=5*h/7 && y<6*h/7) {
      if (avg < 128) {
        pixel.setRed(0.8*avg);
        pixel.setGreen(0);
        pixel.setBlue(2*avg);
      }
      else{
        pixel.setRed(1.2*avg-51);
        pixel.setGreen(2*avg-255);
        pixel.setBlue(255);
      }
    }
    if (y>=6*h/7 && y <= h) {
      if (avg < 128) {
        pixel.setRed(1.6*avg);
        pixel.setGreen(0);
        pixel.setBlue(1.6*avg);
      }
      else{
        pixel.setRed(0.4*avg+153);
        pixel.setGreen(2*avg-255);
        pixel.setBlue(0.4*avg+153);
      }
    }
  }
}

function doGray(){
  if (isImageLoaded(grayImage)) {
    grayFilter();
    grayImage.drawTo(canvas);
  }
}

function doRed(){
  if (isImageLoaded(redImage)){
    redFilter();
    redImage.drawTo(canvas);
  }
}

function doRainbow(){
  if(isImageLoaded(rainbowImage)){
    rainbowFilter()
    rainbowImage.drawTo(canvas);
  }
}

function resetImg(){
  if (isImageLoaded(originalImage)) {
    originalImage.drawTo(canvas);
    grayImage = originalImage;
    redImage = originalImage;
    rainbowImage = originalImage;
    windowImage = originalImage;
  }
}