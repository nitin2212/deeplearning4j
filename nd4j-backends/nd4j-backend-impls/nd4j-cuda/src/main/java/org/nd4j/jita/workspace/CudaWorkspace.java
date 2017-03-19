package org.nd4j.jita.workspace;

import lombok.NonNull;
import org.bytedeco.javacpp.Pointer;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.memory.enums.MemoryKind;
import org.nd4j.linalg.api.memory.pointers.PagedPointer;
import org.nd4j.linalg.memory.abstracts.Nd4jWorkspace;

/**
 * @author raver119@gmail.com
 */
public class CudaWorkspace extends Nd4jWorkspace {
    public CudaWorkspace(@NonNull WorkspaceConfiguration configuration) {
        super(configuration);
    }

    public CudaWorkspace(@NonNull WorkspaceConfiguration configuration, @NonNull String workspaceId) {
        super(configuration, workspaceId);
    }

    @Override
    protected void init() {
        super.init();

        if (currentSize.get() > 0) {
            workspace.setHostPointer(new PagedPointer(memoryManager.allocate(currentSize.get() + 1024, MemoryKind.HOST, true)));

            Pointer.memset(workspace.getHostPointer(), 0, currentSize.get() + 1024);
        }
    }
}
