document.addEventListener('DOMContentLoaded', () => {
    const searchName = document.getElementById('searchName');
    const filterGame = document.getElementById('filterGame');
    const filterSet = document.getElementById('filterSet');
    const filterRarity = document.getElementById('filterRarity');

    function applyFilters() {
        const nameValue = searchName.value.toLowerCase();
        const gameValue = filterGame.value;
        const setValue = filterSet.value;
        const rarityValue = filterRarity.value;

        const cards = document.querySelectorAll('.card-item');
        cards.forEach(card => {
            const cardName = card.getAttribute('data-name').toLowerCase();
            const cardGame = card.getAttribute('data-game');
            const cardSet = card.getAttribute('data-set');
            const cardRarity = card.getAttribute('data-rarity');

            const matchesName = cardName.includes(nameValue);
            const matchesGame = (gameValue === 'all' || cardGame === gameValue);
            const matchesSet = (setValue === 'all' || cardSet === setValue);
            const matchesRarity = (rarityValue === 'all' || cardRarity === rarityValue);

            if (matchesName && matchesGame && matchesSet && matchesRarity) {
                card.style.display = '';
            } else {
                card.style.display = 'none';
            }
        });
    }

    searchName.addEventListener('input', applyFilters);
    filterGame.addEventListener('change', applyFilters);
    filterSet.addEventListener('change', applyFilters);
    filterRarity.addEventListener('change', applyFilters);

    applyFilters();
});

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
        .then(response => {
            if (response.ok) {
                alert('Card added to your collection successfully!');
            } else {
                alert('Failed to add card to your collection.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while adding the card to your collection.');
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
        .then(response => {
            if (response.ok) {
                alert('Card removed from your collection successfully!');
                location.reload();
            } else {
                alert('Failed to remove card from your collection.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while removing the card from your collection.');
        });
    });
});