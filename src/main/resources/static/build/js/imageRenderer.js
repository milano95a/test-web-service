// document.getElementById('questionImg').addEventListener('change', function () {
//     document.getElementById('questionText').removeAttribute('required');
// });
//
// document.getElementById('descImg').addEventListener('change', function () {
//     document.getElementById('descText').removeAttribute('required');
// });
//
// document.getElementById('aImg').addEventListener('change', function () {
//     document.getElementById('aText').removeAttribute('required');
// });
//
// document.getElementById('bImg').addEventListener('change', function () {
//     document.getElementById('bText').removeAttribute('required');
// });
//
// document.getElementById('cImg').addEventListener('change', function () {
//     document.getElementById('cText').removeAttribute('required');
// });
//
// document.getElementById('dImg').addEventListener('change', function () {
//     document.getElementById('dText').removeAttribute('required');
// });


if (document.getElementById('reset') != null) {
    document.getElementById('reset').addEventListener('click', function () {
        setTextValidation('questionText');
        setTextValidation('descText');
        setTextValidation('aText');
        setTextValidation('bText');
        setTextValidation('cText');
        setTextValidation('dText');
    });
}

function hideImageView(imageView) {
    document.getElementById(imageView).style.display = 'none';
}

function setTextValidation(textField) {
    document.getElementById(textField).setAttribute('required', 'required');
}

function readFile(files, imageView, div) {

    console.log("readfile 1");

    if (files && files[0]) {
        var FR = new FileReader();
        FR.addEventListener('load', function (e) {
            document.getElementById(div).style.display = 'block';
            document.getElementById(imageView).src = e.target.result;
        });
        FR.readAsDataURL(files[0]);
    }
}

function readFile(files, imageView, div, inputId) {

    console.log("2");

    if (files && files[0]) {
        var FR = new FileReader();
        FR.addEventListener('load', function (e) {
            document.getElementById(div).style.display = 'block';
            document.getElementById(imageView).src = e.target.result;
            document.getElementById(imageView).addEventListener("click", function () {
                document.getElementById(imageView).src = '';
                clearFileInput(inputId);
            });
        });
        FR.readAsDataURL(files[0]);
    }
}

function clearFileInput(id) {
    var oldInput = document.getElementById(id);

    var newInput = document.createElement("input");

    newInput.type = "file";
    newInput.id = oldInput.id;
    newInput.name = oldInput.name;

    // newInput.className = oldInput.className;
    // newInput.style.cssText = oldInput.style.cssText;
    // TODO: copy any other relevant attributes

    oldInput.parentNode.replaceChild(newInput, oldInput);

    setUp();
}

setUp();

function setUp() {
    setImageViewer('questionImageView', 'divQuestionImageView', 'questionImg', 'isQuestionImgUpdated');
    setImageButtonListener('questionImg', 'questionText', 'isQuestionImgUpdated');

    setImageViewer('descriptionImageView', 'divDescriptionImageView', 'descImg','isDescriptionImgUpdated');
    setImageButtonListener('descImg', 'descText', 'isDescriptionImgUpdated');

    setImageViewer('aImageView', 'divAImageView', 'aImg','isAImgUpdated');
    setImageButtonListener('aImg', 'aText', 'isAImgUpdated');

    setImageViewer('bImageView', 'divBImageView', 'bImg','isBImgUpdated');
    setImageButtonListener('bImg', 'bText', 'isBImgUpdated');

    setImageViewer('cImageView', 'divCImageView', 'cImg','isCImgUpdated');
    setImageButtonListener('cImg', 'cText', 'isCImgUpdated');

    setImageViewer('dImageView', 'divDImageView', 'dImg','isDImgUpdated');
    setImageButtonListener('dImg', 'dText', 'isDImgUpdated');
}

function setImageButtonListener(inputButton, inputText, imgStateCheckbox) {
    document.getElementById(inputButton).addEventListener('change', function () {
        document.getElementById(inputText).removeAttribute('required');
        setImageViewStateToUpdated(imgStateCheckbox);
    });
}

function setImageViewer(imageView, divContainer, inputButton, imgStateCheckbox) {
    document.getElementById(inputButton).addEventListener('change', function () {
        readFile(this.files, imageView, divContainer, inputButton);
    });

    setRemoveButton(imageView, imgStateCheckbox);
}

function setImageViewStateToUpdated(imageViewCheckbox) {
    document.getElementById(imageViewCheckbox).checked = true;
}

function setRemoveButton(imageView, imgStateCheckbox) {
    document.getElementById(imageView).addEventListener('click', function () {
        document.getElementById(imageView).src = '';
        setImageViewStateToUpdated(imgStateCheckbox);
    });
}

if (document.getElementById('reset') != null) {
    document.getElementById('reset').addEventListener('click', function () {
        hideImageView('divQuestionImageView');
        hideImageView('divDescriptionImageView');
        hideImageView('divAImageView');
        hideImageView('divBImageView');
        hideImageView('divCImageView');
        hideImageView('divDImageView');
    });
}

function hideImageView(imageView) {
    document.getElementById(imageView).style.display = 'none';
}

function setTextValidation(textField) {
    document.getElementById(textField).setAttribute('required', 'required');
}