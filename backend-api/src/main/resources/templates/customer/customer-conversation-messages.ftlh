<#import "/layout.ftl" as layout>

<@layout.page title="Conversation with ${conversation.provider.username}">
    <div class="container mt-4">
        <h1 class="mb-3">
            Conversation with ${conversation.provider.username}
        </h1>

        <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center" style="background-color: #f8f9fa;">
                <h5 class="mb-0">
                    Listing: ${conversation.listing.card.name}
                    (${conversation.listing.card.game} - ${conversation.listing.card.set})
                </h5>
            </div>

            <div class="card-body"
                 style="height: 500px; overflow-y: auto; background-color: #ffffff;">
                <#if messages?size == 0>
                    <p class="text-muted">No messages in this conversation yet.</p>
                </#if>

                <#list messages as message>
                    <#if message.sender == "CUSTOMER">
                        <!-- Customer's messages on the right -->
                        <div class="d-flex mb-3 justify-content-end">
                            <div class="p-3 rounded bg-primary text-white border" style="max-width: 70%;">
                                <p class="mb-1">${message.content}</p>
                                <small class="text-muted">${message.createdAtString}</small>
                            </div>
                        </div>
                    <#else>
                        <!-- Provider's messages on the left -->
                        <div class="d-flex mb-3 justify-content-start">
                            <div class="p-3 rounded bg-light text-dark border" style="max-width: 70%;">
                                <p class="mb-1">${message.content}</p>
                                <small class="text-muted">${message.createdAtString}</small>
                            </div>
                        </div>
                    </#if>
                </#list>
            </div>

            <div class="card-footer align-middle">
                <form action="/customer/messages/conversations/${conversation.id}/send"
                      method="post"
                      class="d-flex mb-2 mt-1" style="height: 34px;">
                    <textarea class="form-control me-2"
                              name="content"
                              rows="1"
                              placeholder="Type your message here..."
                              required></textarea>
                    <button type="submit" class="btn btn-success">Send</button>
                </form>
            </div>
        </div>
    </div>
</@layout.page>
