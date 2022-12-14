# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-dontshrink
#-dontoptimize
#
#-keepattributes SourceFile,LineNumberTable
#-keepattributes *Annotation*
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
#-dontwarn okhttp3.**
#-dontwarn okio.**
#-dontwarn retrofit2.Platform$Java8
#
#-dontwarn android.support.design.**
#-keep class android.support.design.** { *; }
#-keep interface android.support.design.** { *; }
#-keep public class android.support.design.R$* { *; }
#
#
#-dontwarn org.jsoup.**
#
#-dontwarn com.google.firebase.**
#-dontwarn com.onesignal.**
#-dontwarn com.crashlytics.sdk.android.**
#-dontwarn com.onesignal.**
#
#-dontwarn com.google.firebase.**
#-dontwarn com.squareup.retrofit2.**
#-dontwarn org.jsoup.**
#
#-dontwarn com.airbnb.android.**
#-dontwarn com.google.android.gms.**
#
#-keepattributes Signature
#-keepattributes *Annotation*
#-keep class com.onesignal.**
#
#-keep class com.google.firebase.**
#-keep class com.squareup.retrofit2.**
#-keep class org.jsoup.**
#
#-keep class com.airbnb.android.**
#-keep class com.google.android.gms.**
#
#
#-dontwarn okio.**
#-keepattributes InnerClasses
#-dontwarn sun.misc.**
#-dontwarn java.lang.invoke.**
#-dontwarn okhttp3.**
#-dontwarn com.anchorfree.sdk.**
#-dontwarn okio.**
#-dontwarn javax.annotation.**
#-dontwarn org.conscrypt.**
#-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
##DNSJava
#-keep class org.xbill.DNS.** {*;}
#-dontnote org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
#-dontwarn org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
#-keep class * implements com.google.gson.TypeAdapterFactory
#-keep class * implements com.google.gson.JsonSerializer
#-keep class * implements com.google.gson.JsonDeserializer
#-keep class com.anchorfree.sdk.SessionConfig { *; }
#-keep class com.anchorfree.sdk.fireshield.** { *; }
#-keep class com.anchorfree.sdk.dns.** { *; }
#-keep class com.anchorfree.sdk.HydraSDKConfig { *; }
#-keep class com.anchorfree.partner.api.ClientInfo { *; }
#-keep class com.anchorfree.sdk.NotificationConfig { *; }
#-keep class com.anchorfree.sdk.NotificationConfig$Builder { *; }
#-keep class com.anchorfree.sdk.NotificationConfig$StateNotification { *; }
#-keepclassmembers public class com.anchorfree.ucr.transport.DefaultTrackerTransport {
#   public <init>(...);
# }
# -keepclassmembers class com.anchorfree.ucr.SharedPrefsStorageProvider{
#    public <init>(...);
# }
# -keepclassmembers class com.anchorfree.sdk.InternalReporting$InternalTrackingTransport{
# public <init>(...);
# }
# -keep class com.anchorfree.sdk.exceptions.* {
#    *;
# }
#
#-keepclassmembers class * implements javax.net.ssl.SSLSocketFactory {
#    final javax.net.ssl.SSLSocketFactory delegate;
#}
#
## https://stackoverflow.com/questions/56142150/fatal-exception-java-lang-nullpointerexception-in-release-build
#-keepclassmembers,allowobfuscation class * {
#  @com.google.gson.annotations.SerializedName <fields>;
#}
#
#-keep class  com.newfastwa.msgemojitype.gbfo.VPN.Connect_VPN_Screen$disconnectFromVnp*{*;}

  -keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
    }


 -dontwarn okio.**
    -keepattributes InnerClasses
    -dontwarn sun.misc.**
    -dontwarn java.lang.invoke.**
    -dontwarn okhttp3.**
    -dontwarn com.anchorfree.sdk.**
    -dontwarn okio.**
    -dontwarn javax.annotation.**
    -dontwarn org.conscrypt.**
    -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
    #DNSJava
    -keep class org.xbill.DNS.** {*;}
    -dontnote org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
    -dontwarn org.xbill.DNS.spi.DNSJavaNameServiceDescriptor
    -keep class * implements com.google.gson.TypeAdapterFactory
    -keep class * implements com.google.gson.JsonSerializer
    -keep class * implements com.google.gson.JsonDeserializer
    -keep class com.anchorfree.sdk.SessionConfig { *; }
    -keep class com.anchorfree.sdk.fireshield.** { *; }
    -keep class com.anchorfree.sdk.dns.** { *; }
    -keep class com.anchorfree.sdk.HydraSDKConfig { *; }
    -keep class com.anchorfree.partner.api.ClientInfo { *; }
    -keep class com.anchorfree.sdk.NotificationConfig { *; }
    -keep class com.anchorfree.sdk.NotificationConfig$Builder { *; }
    -keep class com.anchorfree.sdk.NotificationConfig$StateNotification { *; }
    -keepclassmembers public class com.anchorfree.ucr.transport.DefaultTrackerTransport {
       public <init>(...);
     }
     -keepclassmembers class com.anchorfree.ucr.SharedPrefsStorageProvider{
        public <init>(...);
     }
     -keepclassmembers class com.anchorfree.sdk.InternalReporting$InternalTrackingTransport{
     public <init>(...);
     }
     -keep class com.anchorfree.sdk.exceptions.* {
        *;
     }

    -keepclassmembers class * implements javax.net.ssl.SSLSocketFactory {
        final javax.net.ssl.SSLSocketFactory delegate;
    }

    # https://stackoverflow.com/questions/56142150/fatal-exception-java-lang-nullpointerexception-in-release-build
    -keepclassmembers,allowobfuscation class * {
      @com.google.gson.annotations.SerializedName <fields>;
    }