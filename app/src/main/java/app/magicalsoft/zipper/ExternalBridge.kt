package app.magicalsoft.zipper


class ExternalBridge {
    external fun zip_file(input: String, output: String): Int
    external fun unzip_file(input: String, output: String): Int

}