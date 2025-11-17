function validatePassword(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;
    const form = document.getElementById('providerSignupForm');

    if (password !== confirmPassword) {
        event.preventDefault();
        window.location.href = "/providers/signup?error=password_mismatch"
    }
}


document.getElementById('providerSignupForm').addEventListener('submit', function(event) {
    validatePassword(event);
});