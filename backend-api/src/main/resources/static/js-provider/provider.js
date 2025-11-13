//password validation
function validatePassword() {
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirm-password');
    if (password.value !== confirmPassword.value) {
        confirmPassword.setCustomValidity("Passwords do not match");
    } else {
        confirmPassword.setCustomValidity("");
    }
}

//attach event listeners
document.getElementById('password').addEventListener('input', validatePassword);
document.getElementById('confirm-password').addEventListener('input', validatePassword);