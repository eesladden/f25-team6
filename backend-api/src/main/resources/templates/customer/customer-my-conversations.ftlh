<#import "/layout.ftl" as layout>

<@layout.page title="My Messages">
    <div class="container mt-4">
        <h1 class="mb-4">Messages</h1>

        <#if conversations?size == 0>
            <p class="text-muted">You have no conversations yet.</p>
        <#else>
            <div class="list-group">
                <#list conversations as conversation>
                    <a href="/customer/messages/conversations/my-conversations/${conversation.id}"
                       class="list-group-item list-group-item-action mb-1 rounded">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">
                                Conversation with ${conversation.provider.username}
                            </h5>
                            <small class="text-muted">
                                ${conversation.messages?size} messages
                            </small>
                        </div>
                        <div class="d-flex w-100 justify-content-between mt-1">
                            <p class="mb-1 text-truncate">
                                <strong class="me-1">For Listing:</strong>
                                ${conversation.listing.card.name}
                                ・ ${conversation.listing.card.game}
                                ・ ${conversation.listing.card.set}
                                (${conversation.listing.card.rarity})
                            </p>
                            <small class="text-muted">
                                Last updated: ${conversation.lastUpdatedString}
                            </small>
                        </div>
                    </a>
                </#list>
            </div>
        </#if>
    </div>
</@layout.page>
