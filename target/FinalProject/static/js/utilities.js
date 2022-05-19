function onlyLetters(event) {
    let key = event.keyCode
    return ( (isLowerCaseLetter(key) || isUpperCaseLetter(key)) || key === 8 || key === 32 )
}

function isLowerCaseLetter(letter) {
    return (letter >= 97 && letter <= 122)
}

function isUpperCaseLetter(letter) {
    return (letter >= 65 && leter <= 90)
}