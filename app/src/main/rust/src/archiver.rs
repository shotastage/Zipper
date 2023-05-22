use std::fs::File;
use std::io::{Read, Write};
use zip::{CompressionMethod, write::FileOptions, ZipWriter};
use zip::read::ZipArchive;

// Function to zip a file
fn zip_file(input: &str, output: &str) -> zip::result::ZipResult<()> {
    let path = std::path::Path::new(input);
    let file = File::create(output)?;
    let mut zip = ZipWriter::new(file);
    let options = FileOptions::default()
        .compression_method(CompressionMethod::Stored)
        .unix_permissions(0o755);
    zip.start_file(path.file_name().unwrap().to_str().unwrap(), options)?;
    let mut f = File::open(&path)?;
    let mut buffer = Vec::new();
    f.read_to_end(&mut buffer)?;
    zip.write_all(&*buffer)?;
    zip.finish()?;
    Ok(())
}

// Function to unzip a file
fn unzip_file(input: &str, output: &str) -> zip::result::ZipResult<()> {
    let mut archive = ZipArchive::new(File::open(input)?)?;
    for i in 0..archive.len() {
        let mut file = archive.by_index(i)?;
        let outpath = file.sanitized_name();
        let mut outfile = File::create(&outpath)?;
        std::io::copy(&mut file, &mut outfile)?;
    }
    Ok(())
}
