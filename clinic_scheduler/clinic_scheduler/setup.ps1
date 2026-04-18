$baseUrl = "https://repo1.maven.org/maven2"
$libs = @(
    "org/apache/poi/poi/5.2.3/poi-5.2.3.jar",
    "org/apache/poi/poi-ooxml/5.2.3/poi-ooxml-5.2.3.jar",
    "org/apache/poi/poi-ooxml-lite/5.2.3/poi-ooxml-lite-5.2.3.jar",
    "org/apache/xmlbeans/xmlbeans/5.1.1/xmlbeans-5.1.1.jar",
    "org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar",
    "org/apache/commons/commons-compress/1.21/commons-compress-1.21.jar",
    "commons-io/commons-io/2.11.0/commons-io-2.11.0.jar",
    "org/apache/logging/log4j/log4j-api/2.18.0/log4j-api-2.18.0.jar",
    "org/apache/logging/log4j/log4j-core/2.18.0/log4j-core-2.18.0.jar",
    "com/zaxxer/SparseBitSet/1.2/SparseBitSet-1.2.jar"
)

if (!(Test-Path "lib")) { New-Item -ItemType Directory -Path "lib" }

foreach ($lib in $libs) {
    $fileName = Split-Path $lib -Leaf
    $dest = "lib/$fileName"
    if (!(Test-Path $dest)) {
        Write-Host "Downloading $fileName..."
        Invoke-WebRequest -Uri "$baseUrl/$lib" -OutFile $dest
    }
}

Write-Host "Compiling Java files..."
$classpath = "lib/*"
javac -cp $classpath src/main/java/com/clinic/*.java -d bin

Write-Host "Done! Run with: java -cp 'bin;lib/*' com.clinic.Main"
