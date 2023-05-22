use std::os::raw::c_char;
use std::ffi::{CStr, CString};

#[no_mangle]
pub extern "C" fn rs_zip_file(input: *const c_char, output: *const c_char) -> i32 {
    // Convert C string to Rust string
    let input = unsafe { CStr::from_ptr(input).to_str().unwrap() };
    let output = unsafe { CStr::from_ptr(output).to_str().unwrap() };

    match zip_file_impl(input, output) {
        Ok(_) => 0,
        Err(_) => 1,
    }
}

#[no_mangle]
pub extern "C" fn rs_unzip_file(input: *const c_char, output: *const c_char) -> i32 {
    // Convert C string to Rust string
    let input = unsafe { CStr::from_ptr(input).to_str().unwrap() };
    let output = unsafe { CStr::from_ptr(output).to_str().unwrap() };

    match unzip_file_impl(input, output) {
        Ok(_) => 0,
        Err(_) => 1,
    }
}
