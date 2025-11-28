document.addEventListener('DOMContentLoaded', () => {
    const addToCollectionForm = document.getElementById('addToCollectionForm');

    addToCollectionForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const formData = new FormData(addToCollectionForm);
        const actionUrl = addToCollectionForm.action;

        fetch(actionUrl, {
            method: 'POST',
            body: formData
        })

        .then(() => {
            window.location.href = '/cards/collection';
        });
    });
});

document.addEventListener('DOMContentLoaded', () => {
    const removeCardForm = document.getElementById('removeCardForm');
    removeCardForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const formData = new FormData(removeCardForm);
        const actionUrl = removeCardForm.action;

        fetch(actionUrl, {
            method: 'POST',
            body: formData
        })

        .then(() => {
            window.location.href = '/cards/collection';
        });
    });
});