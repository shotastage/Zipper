use std::io::prelude::*;
use flate2::Compression;
use flate2::write::ZlibEncoder;
use std::io::prelude::*;
use flate2::read::GzDecoder;

use jni::objects::JObject;
use jni::sys::jstring;
use jni::JNIEnv;


#[no_mangle]
pub unsafe extern "C" fn native_zlib_compress() {
    //
}

pub fn compress_zip_b() {
    let mut e = ZlibEncoder::new(Vec::new(), Compression::default());
    e.write_all(b"foo");
    let compressed_bytes = e.finish();
}

pub fn decompress_b() {
    let mut d = GzDecoder::new("...".as_bytes());
    let mut s = String::new();
    d.read_to_string(&mut s).unwrap();
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = compress(2, 2);
        assert_eq!(result, 4);
    }
}
