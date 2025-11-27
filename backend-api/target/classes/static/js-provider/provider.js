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

// Set max date for birthdate input to ensure provider is at least 18 years old
const dobInput = document.getElementById('dobInput');
const today = new Date();
const maxDate = new Date(today.getFullYear() - 18, today.getMonth(), today.getDate());
const formattedMaxDate = maxDate.toISOString().split('T')[0];
dobInput.setAttribute('max', formattedMaxDate);