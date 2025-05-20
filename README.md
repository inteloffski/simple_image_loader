# 📦 simple_image_loader

**simple_image_loader** is a minimal image loading project for Android built with Kotlin Coroutines and Jetpack Compose. It’s designed as an **educational example** to demonstrate the core concepts of image loading, in-memory caching, and UI rendering **without relying on third-party libraries** like Coil, Glide, or Picasso.

> ❗️ Not intended for production use — this is a demo implementation.

---

## 🚀 Features

- 🔄 Load images from a URL using `HttpURLConnection`
- 💾 Simple in-memory cache (`ConcurrentHashMap`)
- 🖼 `@Composable NetworkImage` component for rendering images
- ⚠️ Basic error handling and state support (`loading`, `error`, `loaded`)
- 📚 Clean and easy-to-read source code

---

## 📷 Usage Example

```kotlin
NetworkImage(
    url = "https://example.com/image.png",
    modifier = Modifier.size(200.dp),
    contentDescription = "Sample image",
    loading = { CircularProgressIndicator() },
    error = { Text("Image load failed") }
)
