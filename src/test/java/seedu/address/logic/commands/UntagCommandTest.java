package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalWeddings.WEDDING_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.wedding.Wedding;
import seedu.address.model.wedding.WeddingId;
import seedu.address.testutil.PersonBuilder;

public class UntagCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Wedding defaultWedding = WEDDING_ONE;

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UntagCommand untagCommand = new UntagCommand(outOfBoundIndex, defaultWedding.getWeddingId());

        assertCommandFailure(untagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_personNotTagged_throwsCommandException() {
        // create a person with weddingId not tagged
        WeddingId weddingId = new WeddingId("W12345");
        Person person = new PersonBuilder().build();
        model.addPerson(person);

        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_PERSON, weddingId);

        assertCommandFailure(untagCommand, model, UntagCommand.MESSAGE_PERSON_NOT_TAGGED);
    }
}
