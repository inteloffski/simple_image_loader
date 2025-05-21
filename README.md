# 🖼 Simple Image Loader

A simple and minimalistic image loader implementation for Android with Jetpack Compose support.  
This project demonstrates the basic principles of image loading: downloading from the network, decoding, caching, and displaying.

---

## 📌 Project Goals

- Showcase how to build a custom image loader
- Extensible architecture: easily plug in different HTTP clients (HttpURLConnection, Retrofit, Ktor)
- Minimal dependencies
- Simple integration with Jetpack Compose

---

## 🧱 Main Components

### `ImageLoader`

An interface that defines how an image should be downloaded and decoded.  
Can have multiple implementations using different HTTP clients and decoders.

**Responsibilities:**
- Coordinates image byte fetching
- Passes data to the decoder
- Returns the result as `BitmapState`

---

### `HttpClientEngine`

Interface for retrieving a `ByteArray` from a URL.

**Implementation:**
- `HttpUrlConnectionEngine` – based on the standard `HttpURLConnection`

**Responsibilities:**
- Performs the network request
- Returns the raw image bytes

---

### `ImageDecoder<T>`

An interface for decoding bytes into a specific image type (e.g., `Bitmap`, `Drawable`, or `GifDrawable`).

**Implementation:**
- `BitmapDecoder` – decodes JPEG/PNG into a `Bitmap`

**Responsibilities:**
- Validates the format
- Decodes the image

---

### `MemoryCache`

A simple in-memory cache based on `ConcurrentHashMap` that stores loaded images.

**Responsibilities:**
- Stores images in memory
- Provides fast access without re-fetching

---

### `BitmapState`

A state class that represents the result of the image loading process.

- `Loading` – image is currently being loaded
- `Error` – image failed to load
- `Loaded(bitmap)` – image was successfully loaded
- `Cached(bitmap)` – image was returned from memory cache

---

### `NetworkImage`

A composable function for displaying an image from a URL, with support for:
- Loading indicator
- Error state
- Final image rendering

**Responsibilities:**
- Manages image loading state
- Calls `ImageLoader`
- Displays the appropriate UI based on result

---

## 🚀 Example Usage

```kotlin
@Composable  
fun NetworkImage(  
    url: String,  
    modifier: Modifier = Modifier,  
    contentDescription: String? = null,  
    loader: ImageLoader<Bitmap> = DefaultImageLoader(),  
    error: @Composable () -> Unit = { Text("Error") },  
    loading: @Composable () -> Unit = { CircularProgressIndicator() }  
)
