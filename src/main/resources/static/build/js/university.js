if(document.getElementById('universityImage') != null) {
    document.getElementById('universityImage').addEventListener('change', function () {
        readFile(this.files, 'universityImageView', 'divUniversityImageView');
    });
}
if(document.getElementById('reset') != null) {
    document.getElementById('reset').addEventListener('click', function () {
        hideImageView('universityImageView');
    });
}

function hideImageView(imageView) {
    document.getElementById(imageView).style.display = 'none';
}

// function readFile(files, imageView, div) {
//     if (files && files[0]) {
//         var FR= new FileReader();
//         FR.addEventListener('load', function(e) {
//             document.getElementById(div).style.display = 'block';
//             document.getElementById(imageView).src = e.target.result;
//         });
//         FR.readAsDataURL(files[0]);
//     }
// }

